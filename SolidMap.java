import java.awt.*;
public class SolidMap
{
    int width;
    int height;
    boolean[][] filled;
    public SolidMap(int width, int height)
    {
        this.width = width;
        this.height = height;
        filled = new boolean[width][height];
        for (int i = 0; i < width; i++)
        {
            for (int j = 0; j < height; j++)
            {
                filled[i][j] = false;
            }
        }
    }
    
    public void addSolid(Solid s)
    {
        HitBox hitbox = s.getHitBox();
        for (int i = hitbox.x; i < hitbox.x + hitbox.width && i < width ; i++)
        {
            for (int j = hitbox.y; j < hitbox.y + hitbox.height && j < height; j++)
            {
                filled[i][j] = true;
            }
        }
    }
    
    //not optimized - optimal solution is to base starting array off of current speed
    public boolean hasSolid(HitBox hb)
    {
        int x = hb.x;
        int y = hb.y;
        int w = hb.width;
        int h = hb.height;
        
        int rightBound = x + w;
        int bottomBound = y + h;
        
        if (x < 0)
            x = 0;
        if (y < 0)
            y = 0;
        if (rightBound >= this.width)
            rightBound = this.width - 1;
        if (bottomBound >= this.height)
            bottomBound = this.height - 1;
        
        for (int i = x; i <= rightBound; i++)
        {
            for (int j = y; j <= bottomBound; j++)
            {
                if (filled[i][j])
                    return true;
            }
        }
        return false;
    }
    
    public void draw(Graphics g)
    {
        g.setColor(Color.GREEN);
        for (int i = 0; i < width; i++)
        {
            for (int j = 0; j < height; j++)
            {
                if (filled[i][j])
                    g.fillRect(i, j, 1, 1);
            }
        }
    }
}
