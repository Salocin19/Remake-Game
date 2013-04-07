package Remake;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;
import java.awt.geom.*;
public class SpriteHelper
{
  Sprite salostand;
  Sprite salorun;

  Sprite coolstand;

  static int coolstand_num = 34;
  static int salostand_num = 14;
  static int salorun_num = 8;

  public SpriteHelper()
  {
    salostand = createSpriteFromImages("Salostand", salostand_num);
    coolstand = createSpriteFromImages("Coolstand", coolstand_num);
    salorun = createSpriteFromImages("Salorun", salorun_num);
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
    if (p.currentSpriteName.equals("Salostand"))
      image = salostand.getCurrentImage();
    else
      image = salorun.getCurrentImage();


    if (p.dir == Player.LEFT)
      image = reverseImage(image);

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
  
}
