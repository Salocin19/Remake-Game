package Remake;
import java.io.*;
public class GameState implements Serializable, Cloneable
{
  public Player player1, player2;

  protected Object clone()
  {
    GameState clone = new GameState();
    clone.player1 = player1.clone();
    clone.player2 = player2.clone();
    return clone;
  }

  public GameState()
  {
    /*
    x1 = 0;
    y1 = 50;
    x2 = 100;
    y2 = 100;*/
  }

  public String toString()
  {
    return ""; //this is bad code, just temp
    //return x1 + " " + y1 + " " + x2 + " " + y2;
  }
}
