package Remake;
import java.net.*;
import java.io.*;
import java.lang.Thread;
public class GameClient implements Runnable
{
  Socket s;
  ObjectOutputStream oos;
  MultiPlayerScreen gameScreen;
  String statusMessage;

  //for debugging/ starting without gui
  public GameClient()
  {
    statusMessage = "Starting up client.";
  }

  public GameClient(MultiPlayerScreen gameScreen)
  {
    statusMessage = "Starting up client.";
    this.gameScreen = gameScreen;
  }

  public void run()
  {
    try
    {
      statusMessage = "Connecting to server...";
      s = new Socket("70.112.204.135", 7979);
      startClient();
    }
    catch(Exception e)
    {
      e.printStackTrace();
      System.exit(0);
    }
  }

  void startClient()
  {
    statusMessage = "Setting up communications...";

    try 
    {
      oos = new ObjectOutputStream(s.getOutputStream());
      ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
      ClientStateReceiver csr = new ClientStateReceiver(ois, gameScreen);
      oos.writeObject(gameScreen.playerName);
      new Thread(csr).start();
    }
    catch(Exception e)
    {
      e.printStackTrace();
      System.exit(0);
    }

    statusMessage = "Done! Waiting for other player...";
  }

  public void sendCommand(PlayerCommand command)
  {
    try
    {
      oos.writeObject(command);
    }
    catch(Exception e)
    {
      e.printStackTrace();
      System.exit(0);
    }
  }

  public void sendMessage(String currentMessage)
  {
    PlayerCommand pc = new PlayerCommand(PlayerCommand.MESSAGE, currentMessage);
    new BackgroundSender(oos, pc);
  }

  public String getStatus()
  {
    return statusMessage;
  }

  public static void main(String [] args)
  {
    GameClient c = new GameClient();
    new Thread(c).start();
  }
}
