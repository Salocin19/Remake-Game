package Remake;
import java.awt.*;
public class Projectile implements Collidable
{
  static final int speed = 5;
  HitBox hitbox;
  String spriteName;
  int direction;

  public Projectile(HitBox hb, String s, int direction)
  {
    hitbox = hb;
    spriteName = s;
    this.direction = direction;
  }

  public HitBox getHitBox()
  {
    return hitbox;

  }

  public boolean hasCollision(Collidable c)
  {
    return this.hitbox.collidesWith(c.getHitBox());
  }

  public boolean hasYCollision(Collidable c)
  {
    return this.hitbox.yCollision(c.getHitBox());
  }

  public boolean hasXCollision(Collidable c)
  {
    return this.hitbox.xCollision(c.getHitBox());
  }

  public void draw(Graphics g)
  {
    GameConstants.spriteHelper.drawProjectile(g, this);

  }

  public void run()
  {
    hitbox.x += 5 * direction;

  }

}
