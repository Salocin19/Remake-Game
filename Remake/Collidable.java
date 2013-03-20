
package Remake;

public interface Collidable
{
    public HitBox getHitBox();
    public boolean hasCollision(Collidable c);
    public boolean hasYCollision(Collidable c);
    public boolean hasXCollision(Collidable c);
}
