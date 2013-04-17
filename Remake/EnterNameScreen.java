package Remake;
import java.awt.*;
import java.awt.event.*;
import java.util.*;


public class EnterNameScreen extends GameScreen
{
  public String screenName = "";

  void drawScreen(Graphics g)
  {
    g.drawString("Enter your screen name:",50,50);
    g.drawString(screenName, 50,100);
  }

  void run()
  {

  }

  java.util.List<KeyListener> getKeyListeners()
  {
    java.util.List<KeyListener> l = new LinkedList<KeyListener>();
    l.add(this);
    return l;
  }

  public void keyReleased(KeyEvent e)
  {

  }

  public void keyPressed(KeyEvent e)
  {
    int key = e.getKeyCode();

    if (key == KeyEvent.VK_BACK_SPACE)
    {
      if (screenName.length() != 0)
        screenName = screenName.substring(0,screenName.length() -1);
    }
    else if ((char) key >= '0' && (char) key <= 'z')
      screenName += (char) key;
    else if (key == KeyEvent.VK_ENTER)
    {
            container.switchScreens(new MultiPlayerScreen(container, screenName));
    }
  }

  public void keyTyped(KeyEvent e)
  {

  }

}
