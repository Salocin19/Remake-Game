package Remake;

import java.awt.*;
public class StateDrawer
{
  MultiPlayerScreen mp;

  public StateDrawer(MultiPlayerScreen mp)
  {
    this.mp = mp;
  }

  public void draw(Graphics g)
  {
    drawPlayers(g);
  }

  void drawPlayers(Graphics g)
  {
    GameState gameState = mp.gameState;
    gameState.player1.draw(g);
    gameState.player2.draw(g);
  }
}
