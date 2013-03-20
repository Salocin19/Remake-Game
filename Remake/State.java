package Remake;
import java.io.Serializable;
public class State implements Serializable
{
  public int player1x, player1y, player2x, player2y;

  public State()
  {
    player1x = 0;
    player2x = 0;
    player1y = 0;
    player2y = 0;
  }


}
