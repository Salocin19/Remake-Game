package Remake;
import java.io.*;
import java.util.*;
import java.net.*;
public class GameServer
{
  public boolean quit = false;
  public GameState gameState;
  public ServerStateCalculator ssc;
  ServerSocket ss;
  Socket player1_socket, player2_socket;
  ObjectOutputStream player1_outputStream, player2_outputStream;
  LinkedList<Thread> threads;

  public GameServer()
  {
    System.out.println("Starting up game server...");
    initializeGameData();
    initializeConnections();
    startGame();
    System.out.println("Done with setup! Game should be running.");
  }

  public void restart()
  {
    System.out.println("Starting up game server...");
    initializeGameData();
    initializeConnections();
    startGame();
    System.out.println("Done with setup! Game should be running.");
  }

  void initializeGameData()
  {
    GameFrame.initializeGameConstants();
    HitBoxesMap.initialize();
    addSolidsToMap();
    threads = new LinkedList<Thread>();
    gameState = new GameState();
  }

  void addSolidsToMap()
  {
    Solid floor = new Box(0, GameConstants.GAME_HEIGHT - 100, GameConstants.GAME_WIDTH, 10);
    Solid box = new Box(200, GameConstants.GAME_HEIGHT - 250, GameConstants.GAME_WIDTH, 10);

    GameConstants.solidMap.addSolid(floor);
    GameConstants.solidMap.addSolid(box);
  }

  void initializeConnections()
  {
    System.out.println("Initializing connections...");
    acceptTwoPlayers();
  }

  void acceptTwoPlayers()
  {
    System.out.println("Seeking players...");
    try
    {
      ss = new ServerSocket(7979);
      player1_socket = ss.accept();
      System.out.println("First player found!");
      setupPlayer1();

      player2_socket = ss.accept();
      System.out.println("Second player found!");
      setupPlayer2();

    }
    catch (Exception e)
    {
      quitApplication(e);
    }
  }

  void setupPlayer1()
  {
    System.out.println("Setting up input streams...");
    try
    {
      System.out.println("Setting up player1 input stream...");
      ObjectInputStream ois = new ObjectInputStream(player1_socket.getInputStream());
      CommandReceiver player1_receiver = new CommandReceiver(ois, this, 1);

      System.out.println("Setting up player1 output stream...");
      player1_outputStream = new ObjectOutputStream(player1_socket.getOutputStream());


      Thread t = new Thread(player1_receiver);
      threads.add(t);
      t.start();
    }
    catch(Exception e)
    {
      quitApplication(e);
    }
  }

  void setupPlayer2()
  {
    System.out.println("Setting up output streams...");
    try
    {
      System.out.println("Setting up player2 input stream...");
      ObjectInputStream ois2 = new ObjectInputStream(player2_socket.getInputStream());
      CommandReceiver player2_receiver = new CommandReceiver(ois2, this, 2);

      System.out.println("Setting up player2 output stream...");
      player2_outputStream = new ObjectOutputStream(player2_socket.getOutputStream());

      Thread t = new Thread(player2_receiver);
      threads.add(t);
      t.start();
    }
    catch(Exception e)
    {
      quitApplication(e);
    }
  }

  void startGame()
  {
    try
    {
      ssc = new ServerStateCalculator(this);
      Thread t = new Thread(ssc);
      threads.add(t);
      t.start();
    }
    catch(Exception e)
    {
      quitApplication(e);
    }
  }

  void sendState()
  {
    try
    {
      //System.out.println("Sending " + gameState);
      player1_outputStream.writeObject(gameState.clone());
      player2_outputStream.writeObject(gameState.clone());

      player1_outputStream.flush();
      player2_outputStream.flush();
      //System.out.println("Sent");
    }
    catch(Exception e)
    {
      quitApplication(e);
    }
  }

  void quitApplication(Exception e)
  {
    quit = true;
    e.printStackTrace();
    System.exit(0);
  }

  public static void main(String[] args)
  {
    GameServer g = new GameServer();
  }
}
