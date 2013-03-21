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
    HitBoxesMap.initialize();
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
    int keyCode = e.getKeyCode();
    PlayerCommand pc = null;

    if (keyCode == KeyEvent.VK_RIGHT)
    {
      pc = new PlayerCommand(PlayerCommand.PRESS_RIGHT);
    }

    if (keyCode == KeyEvent.VK_LEFT)
    {
      pc = new PlayerCommand(PlayerCommand.PRESS_LEFT);
    }

    if (keyCode == KeyEvent.VK_UP)
    {
      pc = new PlayerCommand(PlayerCommand.PRESS_UP);
    }
    
    if (pc != null)
      gameClient.sendCommand(pc);

  }

  public void keyReleased(KeyEvent e)
  {
    int keyCode = e.getKeyCode();
    PlayerCommand pc = null;
    if (keyCode == KeyEvent.VK_RIGHT)
    {
      pc = new PlayerCommand(PlayerCommand.RELEASE_RIGHT);
    }

    if (keyCode == KeyEvent.VK_LEFT)
    {
      pc = new PlayerCommand(PlayerCommand.RELEASE_LEFT);
    }

    if (keyCode == KeyEvent.VK_UP)
    {
      pc = new PlayerCommand(PlayerCommand.RELEASE_UP);
    }

    if (pc != null)
      gameClient.sendCommand(pc);

  }

  public void keyTyped(KeyEvent e){}
  
} 
