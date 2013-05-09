package Remake;
import java.io.*;

public class BackgroundSender implements Runnable
{
  ObjectOutputStream oos;
  Object toSend;
  GameServer server = null;

  public BackgroundSender(ObjectOutputStream oos, Object toSend)
  {
    this.oos = oos;
    this.toSend = toSend;
    Thread th = new Thread(this);
    th.setPriority(Thread.MIN_PRIORITY);
    th.start();
  }

  public BackgroundSender(ObjectOutputStream oos, Object toSend, GameServer server)
  {
    this.oos = oos;
    this.toSend = toSend;
    this.server = server;
    Thread th = new Thread(this);
    th.setPriority(Thread.MIN_PRIORITY);
    th.start();
  }

  public void run()
  {
    try
    {
      oos.writeObject(toSend);
      oos.flush();
    }
    catch (Exception e)
    {
      server.quitApplication(e);
    }
  }
}
