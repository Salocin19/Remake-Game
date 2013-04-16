package Remake;

public class ServerStart implements Runnable
{
  GameServer g;
  public static void main (String[] args)
  {
    new ServerStart();
  }

  public ServerStart()
  {
    g = new GameServer();
    Thread t = new Thread(this);
    t.run();
  }

  public void run()
  {
    while (!g.quit)
    {
      try
      {
        Thread.sleep(100);
      }
      catch (InterruptedException e)
      {

      }
    }
    restart();

  }

  void restart()
  {
    System.out.println("Restarting server...");
    g.closeSockets();
    g = new GameServer();
    run();

  }

}
