package Remake;
import java.awt.*;
import java.awt.image.*;
public class Sprite
{
  BufferedImage image;

  public Sprite(BufferedImage image)
  {
    this.image = image;
  }

  public BufferedImage getCurrentImage()
  {
    return image;
  }
}
