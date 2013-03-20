package Remake;
import java.awt.Graphics;
import java.awt.event.*;
import java.util.*;
public class MultiPlayerScreen extends GameScreen
{
  GameClient gameClient;
  GameState gameState;
  StateDrawer drawer;

  public MultiPlayerScreen(GamePanel gp)
  {
    this.container = gp;
    gameState = new GameState();
    drawer = new StateDrawer(this);
  }

  public void initializeConnections()
  {
    gameClient = new GameClient(this);
    new Thread(gameClient).start();
  }

  public void drawScreen(Graphics g)
  {
    drawer.draw(g);
  }

  public void run()
  {
    return;
  }

  public List<KeyListener> getKeyListeners()
  {
    LinkedList<KeyListener> listeners = new LinkedList<KeyListener>();
    listeners.add(this);
    return listeners;
  }

  public void keyPressed(KeyEvent e)
  {
    if (e.getKeyCode() == KeyEvent.VK_RIGHT)
    {
      PlayerCommand pc = new PlayerCommand(PlayerCommand.PRESS_RIGHT);
      gameClient.sendCommand(pc);
    }

    if (e.getKeyCode() == KeyEvent.VK_LEFT)
    {
      PlayerCommand pc = new PlayerCommand(PlayerCommand.PRESS_LEFT);
      gameClient.sendCommand(pc);
    }

  }

  public void keyReleased(KeyEvent e)
  {

  }

  public void keyTyped(KeyEvent e){}
  
} 
