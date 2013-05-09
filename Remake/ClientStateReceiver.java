package Remake;
import java.lang.Thread;
import java.util.*;
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
        Object o = in.readObject();
        if (o instanceof String)
          gameScreen.chatBox.addToChatHistory((String) o);
        else
        {
          LinkedList<GameState> s = (LinkedList<GameState>) o;
          //System.out.println("Received state with " + s); 
          gameScreen.receivedStates = s;
        }
      }
      catch(Exception e)
      {
        e.printStackTrace();
        System.exit(0);
      }
    }
  }

}
