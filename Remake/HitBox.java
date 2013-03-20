package Remake;
//hitbox used for collision detection
public class HitBox
{
    public int x, y, width, height, originX, originY;
    
    public HitBox(int x, int y, int width, int height)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    public HitBox(int x, int y, int width, int height, int originX, int originY)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.originX = originX;
        this.originY = originY;
    }
    
    public boolean collidesWith(HitBox otherHB)
    {
        boolean xCollision = xCollision(otherHB);
        boolean yCollision = yCollision(otherHB);
        return (xCollision && yCollision);
    }
    
    public boolean xCollision(HitBox otherHB)
    {
        if (otherHB.width < this.width)
        {
            return otherHB.xCollision(this);
        }
        boolean left = (this.x >= otherHB.x && this.x <= otherHB.x + otherHB.width);
        boolean right = (this.x + this.width >= otherHB.x && this.x + this.width <= otherHB.x + otherHB.width);
        return left || right;
    }
    
    public boolean yCollision(HitBox otherHB)
    {
        if (otherHB.height < this.height)
        {
            return otherHB.yCollision(this);
        }
        boolean top = (this.y >= otherHB.y && this.y <= otherHB.y + otherHB.height);
        boolean bottom = (this.y + this.height >= otherHB.y && this.y + this.height <= otherHB.y + otherHB.height);
        return top || bottom;
    }
}
