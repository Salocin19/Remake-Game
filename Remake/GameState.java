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
    player1 = new Player();
    player2 = new Player();
  }

  public String toString()
  {
    return player1 + " " + player2; //this is bad code, just temp
    //return x1 + " " + y1 + " " + x2 + " " + y2;
  }
}
