//basic tile
package Remake;
import java.awt.*;
public class Box implements Solid
{
    HitBox hitbox;
    
    public Box(int x, int y, int width, int height)
    {
        hitbox = new HitBox(x,y,width,height);
    }
    
    public HitBox getHitBox()
    {
        return hitbox;
    }
    
    public void draw(Graphics g)
    {
        g.fillRect(hitbox.x,hitbox.y,hitbox.width,hitbox.height);
    }
    
    
    
}
