package Remake;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.*;
import java.util.*;
public class MultiPlayerScreen extends GameScreen
{
  GameClient gameClient;
  GameState gameState;
  ChatBox chatBox;
  StateDrawer drawer;
  ArrayList<Solid> solids;
  LinkedList<GameState> receivedStates;
  String playerName;
  int gameTime;
  boolean chatting = false;

  public MultiPlayerScreen(GamePanel gp, String playerName)
  {
    this.playerName = playerName;
    HitBoxesMap.initialize();
    this.container = gp;
    gameState = null;
    gameTime = 0;
    drawer = new StateDrawer(this);
    receivedStates = new LinkedList<GameState>();
    chatBox = new ChatBox(GameConstants.GAME_WIDTH, 0, this);
    initSolids();
    addChatBox();
  }

  void addChatBox()
  {
    container.containerFrame.setSize(700,500);
  }

  void initSolids()
  {
    int gameHeight = GameConstants.GAME_HEIGHT;
    int gameWidth = GameConstants.GAME_WIDTH;
    Solid floor = new Box(0, gameHeight - 100, gameWidth, 10);
    Solid box = new Box(200,gameHeight - 250, gameWidth - 200, 10);
    Solid leftWall = new Box(0,0,3, gameHeight);
    Solid rightWall = new Box(gameWidth - 3, 0, 3, gameHeight);
    solids = new ArrayList<Solid>(0);
    solids.add(floor);
    solids.add(box);
    solids.add(leftWall);
    solids.add(rightWall);
  }

  public void initializeConnections()
  {
    gameClient = new GameClient(this);
    new Thread(gameClient).start();
  }

  public void drawScreen(Graphics g)
  {
    if (gameState != null)
    {
      gameTime++;
      if (gameTime <= 50)
      {
        g.setColor(Color.red);
        g.drawString("START!", 200, 200);
        g.setColor(Color.black);
      }
      if (chatting)
        g.drawString("You are typing...press ENTER to get back in the game!", 200, 200);
      drawSolids(g);
      drawer.draw(g);
      chatBox.draw(g);
    }
    else //waiting on other player
    {
      g.drawString("Waiting on other player...", 200, 200);
    }
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
    if (!chatting)
      actionPressed(e);
    else
      typingPressed(e);
  } 

  public void actionPressed(KeyEvent e)
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

    if (keyCode == KeyEvent.VK_Z)
    {
      pc = new PlayerCommand(PlayerCommand.SHOOT);
    }

    if (keyCode == KeyEvent.VK_ENTER)
      chatting = true;
    
    if (pc != null)
      gameClient.sendCommand(pc);

  }

  public void typingPressed(KeyEvent e)
  {
    int key = e.getKeyCode();

    if (key == KeyEvent.VK_BACK_SPACE)
    {
      chatBox.backSpace();
    }
    else if (key == KeyEvent.VK_SPACE)
    {
      chatBox.addToCurrent(" ");
    }
    else if ((char) key >= '0' && (char) key <= 'z')
      chatBox.addToCurrent((char) key);
    else if (key == KeyEvent.VK_ENTER)
    {
      chatting = false;
      chatBox.flush();
    }
  }

  public void keyReleased(KeyEvent e)
  {
    if (!chatting)
      actionReleased(e);
  }

  public void actionReleased(KeyEvent e)
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
