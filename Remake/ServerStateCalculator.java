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
        Thread.sleep(GameConstants.GAME_SPEED);
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
    server.gameState.player1.run();
    server.gameState.player2.run();
    System.out.println(server.gameState.player1.projectiles.size());
    server.manageCommunications();
  }

  void executePlayerCommand(PlayerCommand pc, int playerNumber)
  {
    int command = pc.command;
    Player p;
    if (playerNumber == 1)
      p = server.gameState.player1;
    else
      p = server.gameState.player2;


    switch (command)
    {
      case PlayerCommand.PRESS_RIGHT : p.run_right();
                                         break;
      case PlayerCommand.PRESS_LEFT : p.run_left();
                                        break;
      case PlayerCommand.PRESS_UP : {p.jump();p.createProjectile();}
                                      break;
      case PlayerCommand.RELEASE_RIGHT : p.stop_running_right();
                                          break;
      case PlayerCommand.RELEASE_LEFT : p.stop_running_left();
    }


  }


}
