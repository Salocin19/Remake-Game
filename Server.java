import java.net.*;
import java.util.*;
import java.io.*;
public class Server
{
    ServerSocket ss;
    Socket socket1;
    Socket socket2;
    public Server()
    {
        try
        {
            ss = new ServerSocket(7979);
            socket1 = ss.accept();
            System.out.println("Socket 1 accepted!");
            socket2 = ss.accept();
            System.out.println("Socket 2 accepted!");
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
            ObjectOutputStream out2 = new ObjectOutputStream(socket2.getOutputStream());
            Scanner scan = new Scanner(System.in);
            while (scan.hasNextInt())
            {
                out1.writeInt(scan.nextInt());
                out1.flush();
                System.out.println("out1 written ");
                out2.writeInt(scan.nextInt() + 3);
                out2.flush();
                System.out.println("out2 written ");
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