package Remake;
import java.net.*;
import java.util.*;
import java.io.*;
public class Server
{
    ServerSocket ss;
    Socket socket1;
    public Server()
    {
        try
        {
            ss = new ServerSocket(7979);
            socket1 = ss.accept();
            System.out.println("Socket 1 accepted!");
            startApplication();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.exit(0);
        }
    }
    
    void startApplication()
    {
        try
        {
            ObjectOutputStream out1 = new ObjectOutputStream(socket1.getOutputStream());
            Scanner scan = new Scanner(System.in);
            int counter = 0;
            while (scan.hasNextLine())
            {
                String x = scan.nextLine();
                State newState = new State();
                //newState.x = counter;
                //newState.y = counter;
                out1.writeObject(newState);
                out1.flush();
                System.out.println("sent. ");
                counter++;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.exit(0);
        }
    }
    
    public static void main(String[] args)
    {
        Server s = new Server();
    }
}
