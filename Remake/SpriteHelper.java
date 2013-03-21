package Remake;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;
public class SpriteHelper
{
  Sprite salostand;

  public SpriteHelper()
  {
    salostand = createSpriteFromImage("Sprites/Salocin.png");
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

  public BufferedImage getCurrentImage(Player p)
  {
    return salostand.getCurrentImage();
  }
  
}
