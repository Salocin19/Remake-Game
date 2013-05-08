package Remake;
import java.awt.*;

public class ChatBox
{
  int x, y;
  final int width = 200;
  final int height = 500;
  String currentMessage = "";

  final int MAX_SIZE = 50;

  public ChatBox(int x, int y)
  {
    this.x = x;
    this.y = y;
  }

  public void addToCurrent(String s)
  {
    currentMessage += s;
  }

  public void addToCurrent(char c)
  {
    currentMessage += c;
  }

  public void flush()
  {
    sendMessage();
    currentMessage = "";
  }

  public void sendMessage()
  {
    
  }

  public void backSpace()
  {
    if (currentMessage.length() != 0)
      currentMessage = currentMessage.substring(0, currentMessage.length() -1);
  }

  public void draw (Graphics g)
  {
    g.setColor(Color.black);
    g.fillRect(x,y,width, height / 4 * 3);
    g.setColor(Color.white);
    g.fillRect(x, y + height / 4 * 3, width, height - height / 4 * 3 );
    g.setColor(Color.black);
    g.drawString(currentMessage, x, y + height / 4 * 3 + 20);
    g.setColor(Color.black);
    
  }


}
