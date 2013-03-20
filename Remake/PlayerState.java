package Remake;
import java.io.*;
public class PlayerState implements Serializable
{
  public int x, y;

  public PlayerState()
  {
    x = 0;
    y = 0;
  }

  public String toString()
  {
    return x + "," + y;
  }
}
