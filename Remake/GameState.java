package Remake;
import java.io.*;
public class GameState implements Serializable, Cloneable
{
  public int x1, x2, y1, y2;

  protected Object clone()
  {
    GameState clone = new GameState();
    clone.x1 = x1;
    clone.x2 = x2;
    clone.y1 = y1;
    clone.y2 = y2;
    return clone;
  }

  public GameState()
  {
    x1 = 0;
    y1 = 50;
    x2 = 100;
    y2 = 100;
  }

  public String toString()
  {
    return x1 + " " + y1 + " " + x2 + " " + y2;
  }
}
