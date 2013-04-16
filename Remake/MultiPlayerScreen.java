package Remake;
import java.awt.Graphics;
import java.awt.event.*;
import java.util.*;
public class MultiPlayerScreen extends GameScreen
{
  GameClient gameClient;
  GameState gameState;
  StateDrawer drawer;
  ArrayList<Solid> solids;
  LinkedList<GameState> receivedStates;

  public MultiPlayerScreen(GamePanel gp)
  {
    HitBoxesMap.initialize();
    this.container = gp;
    gameState = new GameState();
    drawer = new StateDrawer(this);
    receivedStates = new LinkedList<GameState>();
    initSolids();
  }

  void initSolids()
  {
    int gameHeight = GameConstants.GAME_HEIGHT;
    int gameWidth = GameConstants.GAME_WIDTH;
    Solid floor = new Box(0, gameHeight - 100, gameWidth, 10);
    Solid box = new Box(200,gameHeight - 250, gameWidth, 10);
    solids = new ArrayList<Solid>(0);
    solids.add(floor);
    solids.add(box);
  }

  public void initializeConnections()
  {
    gameClient = new GameClient(this);
    new Thread(gameClient).start();
  }

  public void drawScreen(Graphics g)
  {
    drawSolids(g);
    drawer.draw(g);
  }

  void drawSolids(Graphics g)
  {
    for (Solid s : solids)
    {
      s.draw(g);
    }
  }

  public void run()
  {
    if (receivedStates.size() != 0)
      gameState = receivedStates.poll();
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
