package Remake;
import java.lang.Thread;
import java.io.*;
public class ClientStateReceiver implements Runnable
{
  ObjectInputStream in;
  MultiPlayerScreen gameScreen;

  public ClientStateReceiver(ObjectInputStream in, MultiPlayerScreen gameScreen)
  {
    this.in = in;
    this.gameScreen = gameScreen;
  }

  public void run()
  {
    while(true)
    {
      try
      {
        GameState s = (GameState)in.readObject();
        //System.out.println("Received state with " + s); 
        gameScreen.gameState = s;
      }
      catch(Exception e)
      {
        e.printStackTrace();
        System.exit(0);
      }
    }
  }

}
