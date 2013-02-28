import java.lang.Thread;
import java.io.*;
public class ReceiverThread implements Runnable
{
    ObjectInputStream in;

    public ReceiverThread(ObjectInputStream in)
    {
        this.in = in;
    }

    public void run()
    {
        while(true)
        {
            try
            {
                System.out.println("waiting to read...");
                System.out.println(in.readInt());
                System.out.println("read.");
            }
            catch (Exception e)
            {
                e.printStackTrace();
                System.exit(0);
            }
        }
    }
}