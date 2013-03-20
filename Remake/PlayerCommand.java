package Remake;
import java.io.*;
public class PlayerCommand implements Serializable
{
  public static final int PRESS_RIGHT = 1;
  public static final int PRESS_LEFT = 2;

  public int command;

  public PlayerCommand (int command)
  {
    this.command = command;
  }
}
