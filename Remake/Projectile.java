package Remake;
import java.io.Serializable;
import java.awt.*;
public class Projectile implements Collidable, Serializable
{
  static final int speed = 10;
  HitBox hitbox;
  String spriteName;
  int direction;
  public int time = 0;

  public Projectile(HitBox hb, String s, int direction)
  {
    hitbox = hb;
    spriteName = s;
    this.direction = direction;
  }

  public Projectile clone()
  {
    Projectile p = new Projectile(hitbox.clone(), spriteName, direction);
    return p;
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
    time ++;
    hitbox.x += speed * direction;

  }

}
