import java.net.*;
import java.io.*;
public class Client
{
    Socket s;
    public Client()
    {
        try
        {
            s = new Socket("localhost", 7979);
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
            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
            ReceiverThread rt = new ReceiverThread(ois);
            new Thread(rt).start();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.exit(0);
        }
    }
    
    public static void main (String [] args)
    {
        Client c = new Client();
    }
}