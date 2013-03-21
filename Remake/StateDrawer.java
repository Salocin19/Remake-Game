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
    //g.drawString("Player 1", gameState.x1, gameState.y1);
    //g.drawString("Player 2", gameState.x2, gameState.y2);
  }
}
