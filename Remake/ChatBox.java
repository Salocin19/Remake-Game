package Remake;
import java.awt.*;

public class ChatBox
{
  int x, y;
  final int width = 200;
  final int height = 500;
  String currentMessage = "";
  MultiPlayerScreen multiPlayerScreen;
  NQueue history;

  

  final int MAX_SIZE = 50;
  final int HISTORY_SIZE = 5;



  public ChatBox(int x, int y, MultiPlayerScreen ms)
  {
    this.x = x;
    this.y = y;
    multiPlayerScreen = ms;
    history = new NQueue(HISTORY_SIZE);
  }

  public void addToCurrent(String s)
  {
    currentMessage += s;
  }

  public void addToChatHistory(String s)
  {
    history.add(s);
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
    multiPlayerScreen.gameClient.sendMessage(currentMessage);
    
  }

  public void backSpace()
  {
    if (currentMessage.length() != 0)
      currentMessage = currentMessage.substring(0, currentMessage.length() -1);
  }

  public void draw (Graphics g)
  {
    //draw chat history
    g.setColor(Color.black);
    g.fillRect(x,y,width, height / 4 * 3);
    g.setColor(Color.white);
    int y_offset = 20;
    for (String s : history.list())
    {
      g.drawString(s, x, y_offset);
      y_offset += 20;
      
    }


    //draw what you are typing
    g.setColor(Color.white);
    g.fillRect(x, y + height / 4 * 3, width, height - height / 4 * 3 );
    g.setColor(Color.black);
    g.drawString(currentMessage, x, y + height / 4 * 3 + 20);
    g.setColor(Color.black);
    
  }


}
