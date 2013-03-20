package Remake;
public class ServerStateCalculator implements Runnable
{
  GameServer server;

  public ServerStateCalculator(GameServer server)
  {
    this.server = server;
  }

  public void run()
  {
    while (true)
    {
      try
      {
        gameLoop();
        if (server.quit)
          return;
        Thread.sleep(30);
      }
      catch(InterruptedException e)
      {
        

      }
      catch (Exception e)
      {
        server.quitApplication(e);
      }
    }
  }

  void gameLoop() throws Exception
  {
    server.sendState();
  }

  void executePlayerCommand(PlayerCommand pc, int playerNumber)
  {
    if (playerNumber == 1)
    {
    if (pc.command == PlayerCommand.PRESS_RIGHT)
      server.gameState.x1 += 1;
    else if (pc.command == PlayerCommand.PRESS_LEFT)
      server.gameState.x1 -= 1;
    }
    else
    {
    if (pc.command == PlayerCommand.PRESS_RIGHT)
      server.gameState.x2 += 1;
    else if (pc.command == PlayerCommand.PRESS_LEFT)
      server.gameState.x2 -= 1;

    }
  }


}
