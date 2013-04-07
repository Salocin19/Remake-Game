package Remake;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;
import java.awt.geom.*;
import java.util.*;
public class SpriteHelper
{
  HashMap<String, Sprite> sprites;


  static int salostand_num = 14;
  static int salorun_num = 8;
  static int salojump_num = 1;
  static int salofall_num = 1;

  static int darkball_num = 10;

  static int coolstand_num = 34;

  public SpriteHelper()
  {
    sprites = new HashMap<String, Sprite>();

    Sprite salostand = createSpriteFromImages("Salostand", salostand_num);
    Sprite salorun = createSpriteFromImages("Salorun", salorun_num);
    Sprite salojump = createSpriteFromImages("Salojump", salojump_num);
    Sprite salofall = createSpriteFromImages("Salofall", salofall_num);

    sprites.put("Salostand", salostand);
    sprites.put("Salorun", salorun);
    sprites.put("Salojump", salojump);
    sprites.put("Salofall", salofall);

    Sprite darkball = createSpriteFromImages("Darkball", darkball_num);
    sprites.put("Darkball", darkball);

    Sprite coolstand = createSpriteFromImages("Coolstand", coolstand_num);

    sprites.put("Coolstand", coolstand);
  }

  private Sprite createSpriteFromImage(String fileName)
  {
    BufferedImage bimage = null;
    try
    {
      File spriteFile = new File(fileName);
      bimage = ImageIO.read(spriteFile);

    }
    catch(Exception e)
    {
      e.printStackTrace();
      System.exit(0);
    }
    return new Sprite(bimage);
  }

  private Sprite createSpriteFromImages(String spritename, int numImages)
  {
    String folder = "Sprites/" + spritename + "/";
    try
    {
      BufferedImage[] images = new BufferedImage[numImages];
      for (int i = 0; i < numImages; i++)
      {
        String filename = folder + "img" + i + ".png";
        images[i] = ImageIO.read(new File(filename));
      }
      return new Sprite(images);
    }
    catch(Exception e)
    {
      System.out.println("Could not read location - " + folder);
      e.printStackTrace();
      System.exit(0);
    }
    return null;
  }

  public BufferedImage getCurrentImage(Player p)
  {
    BufferedImage image;
    image = sprites.get(p.currentSpriteName).getCurrentImage();


    if (p.dir == Player.LEFT)
      image = reverseImage(image);

    return image;
  }

  public BufferedImage getCurrentImage(String spriteName)
  {
    BufferedImage image;
    image = sprites.get(spriteName).getCurrentImage();
    return image;
  }

  BufferedImage reverseImage(BufferedImage i)
  {
    AffineTransform tx = AffineTransform.getScaleInstance(-1,1);
    tx.translate(-i.getWidth(null), 0);
    AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
    i = op.filter(i,null);
    return i;
  }

  public void drawProjectile(Graphics g, Projectile p)
  {
    BufferedImage image = getCurrentImage(p.spriteName);
    HitBox hitbox = p.hitbox;
    g.drawImage(image, hitbox.x - hitbox.originX, hitbox.y - hitbox.originY, null);
  } 
  
}
