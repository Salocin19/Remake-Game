package Remake;
import java.awt.*;
import java.awt.image.*;
public class Sprite
{
  BufferedImage[] images;
  int current = 0;

  public Sprite(BufferedImage image)
  {
    images = new BufferedImage[1];
    images[0] = image;
  }

  public Sprite clone()
  {
    return new Sprite(images);
  }

  public Sprite(BufferedImage[] i)
  {
    images = i;
  }

  public BufferedImage getCurrentImage()
  {
    BufferedImage i = images[current];
    current++;
    if (current >= images.length)
      current = 0;
    return i;
  }
}
