package Remake;
import java.io.*;
public class PlayerCommand implements Serializable
{
  public static final int PRESS_RIGHT = 1;
  public static final int PRESS_LEFT = 2;
  public static final int PRESS_UP = 3;

  public static final int RELEASE_RIGHT = 101;
  public static final int RELEASE_LEFT = 102;
  public static final int RELEASE_UP = 103;

  public static final int SHOOT = 1001;

  public static final int MESSAGE = 99;

  public int command;
  public String message = "";

  public PlayerCommand (int command)
  {
    this.command = command;
  }

  public PlayerCommand (int command, String message)
  {
    this.command = command;
    this.message = message;
  }
}
