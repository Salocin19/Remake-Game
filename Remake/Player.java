package Remake;
import java.util.*;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
public class Player implements Collidable, KeyListener
{
    SolidMap solidMap;

    BufferedImage sprite;
    HitBox hitbox;
    int gravity = 1;
    int y_speed = 0;
    int x_speed = 0;
    int jump_speed = 20;
    int dir; //1 = right, -1 = left
    int run_speed = 5;
    int max_y_speed = 10;
    
    final int LEFT = -1;
    final int RIGHT = 1;
    
    
    boolean airborne = true;
    boolean running = false;
    
    public Player (String fileName, SolidMap sm)
    {
        File spriteFile = new File(fileName);
        hitbox = HitBoxesMap.getHitBox(spriteFile.getName());
        solidMap = sm;
        try
        {
            sprite = ImageIO.read(spriteFile);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void draw(Graphics g)
    {
        g.drawImage(sprite, hitbox.x - hitbox.originX, hitbox.y - hitbox.originY, null);
        g.drawRect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
    }
    
    public HitBox getHitBox()
    {
        return hitbox;
    }
    
    public boolean hasCollision(Collidable c)
    {
        return (this.hitbox.collidesWith(c.getHitBox()));
    }
    
    public boolean hasYCollision(Collidable c)
    {
        return (this.hitbox.yCollision(c.getHitBox()));
    }
    
    public boolean hasXCollision(Collidable c)
    {
        return (this.hitbox.xCollision(c.getHitBox()));
    }
    
    
    public void run()
    {
        update_state();
        update_y_position();
        update_x_position();
        checkSolidCollisions();
    }
    
    //modifies all internal variables except hitbox.x, hitbox.y
    void update_state()
    {
        updateXSpeed();
        updateYSpeed();
    }
    
    void checkSolidCollisions()
    {
        checkAirborne();
    }
    
    void checkAirborne()
    {
        if (!isFree(hitbox.x, hitbox.y+1))
        {
            airborne = false;
        }
        else
            airborne = true;
    }
    
    void updateXSpeed()
    {
        if (running)
        {
            if (dir == RIGHT)
                x_speed = run_speed;
            else
                x_speed = -run_speed;
        }
        else
        {
            x_speed = 0;
        }
    }
    
    void updateYSpeed()
    {
        if (airborne == false)
        {
            y_speed = 0;
        }
        else
        {
            y_speed += gravity;
            if (y_speed > max_y_speed)
                y_speed = max_y_speed;
        }
    }
    
    //only modifies hitbox.y
    void update_y_position()
    {
        if (isFree(hitbox.x, hitbox.y+y_speed))
        {
            hitbox.y += y_speed;
        }
        else
        {
            int sign;
            if (y_speed > 0)
                sign = 1;
            else if (y_speed < 0)
                sign = -1;
            else
                return;
            while (isFree(hitbox.x,hitbox.y+sign))
            {
                hitbox.y+=sign;
            }
            y_speed = 0;
        }
    }
    
    //only modifies hitbox.x
    void update_x_position()
    {
        if (isFree(hitbox.x+x_speed, hitbox.y))
            hitbox.x += x_speed;
        else
        {
            int sign;
            if (x_speed > 0)
                sign = 1;
            else if (x_speed < 0)
                sign = -1;
            else
                return;
            while (isFree(hitbox.x+sign, hitbox.y))
            {
                hitbox.x+=sign;
            }
        }
    }
    
    boolean isFree(int x, int y)
    {
        HitBox hb = new HitBox(x,y,hitbox.width, hitbox.height);
        return !solidMap.hasSolid(hb);
    }
    
    void jump()
    {
        if (!airborne)
        {
            airborne = true;
            y_speed = -jump_speed;
        }
    }
    
    void run_right()
    {
        running = true;
        dir = RIGHT;
        
    }
    
    void run_left()
    {
        running = true;
        dir = LEFT;
    }
    
    public void keyPressed(KeyEvent e)
    {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_RIGHT)
        {
            run_right();
        }
        else if (key == KeyEvent.VK_LEFT)
        {
            run_left();
        }
        else if (key == KeyEvent.VK_UP)
        {
            jump();
        }
    }
    public void keyTyped(KeyEvent e){};
    public void keyReleased(KeyEvent e)
    {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_RIGHT)
        {
            if (dir == RIGHT)
                running = false;
        }
        else if (key == KeyEvent.VK_LEFT)
        {
            if (dir == LEFT)
                running = false;
        }
    }
    
    void pause()
    {
        running = false;
    }
}