package Remake;
import java.io.*;
import java.net.*;
public class AcceptorThread implements Runnable
{
  ServerSocket ss;
  GameServer server;
  Socket player1_socket;
  Socket player2_socket;
  
  public AcceptorThread(GameServer server, ServerSocket ss, Socket player1_socket, Socket player2_socket)
  {
    this.server = server;
    this.ss = ss;
    this.player1_socket = player1_socket;
    this.player2_socket = player2_socket;
  }

  public void run()
  {
    return;

  }
}
