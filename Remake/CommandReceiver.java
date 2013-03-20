package Remake;
import java.io.*;
import java.util.*;
public class CommandReceiver implements Runnable
{
  ObjectInputStream ois;
  GameServer server;
  int playerNumber;
  
  public CommandReceiver(ObjectInputStream ois, GameServer server, int playerNumber)
  {
    this.ois = ois;
    this.server = server;
    this.playerNumber = playerNumber;
  }

  public void run()
  {
    while (true)
    {
      if (server.quit)
        return;
      try
      {
        //System.out.println("Reading...");
        PlayerCommand pc = (PlayerCommand) ois.readObject();
        server.ssc.executePlayerCommand(pc, playerNumber);
      }
      catch(Exception e)
      {
        server.quitApplication(e);
      }
    }
  }
}
