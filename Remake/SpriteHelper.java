package Remake;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;
public class SpriteHelper
{
  Sprite salostand;
  Sprite coolstand;

  static int coolstand_num = 34;

  public SpriteHelper()
  {
    salostand = createSpriteFromImage("Sprites/Salocin.png");
    coolstand = createSpriteFromImages("coolstand");
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

  private Sprite createSpriteFromImages(String spritename)
  {
    try
    {
      String folder = "Sprites/" + spritename + "/";
      BufferedImage[] images = new BufferedImage[SpriteHelper.coolstand_num];
      for (int i = 0; i < SpriteHelper.coolstand_num; i++)
      {
        String filename = folder + "img" + i + ".png";
        images[i] = ImageIO.read(new File(filename));
      }
      return new Sprite(images);
    }
    catch(Exception e)
    {
      e.printStackTrace();
      System.exit(0);
    }
    return null;
  }

  public BufferedImage getCurrentImage(Player p)
  {
    return coolstand.getCurrentImage();
  }
  
}
