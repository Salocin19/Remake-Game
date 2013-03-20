package Remake;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
public class OptionsMenuScreen extends GameScreen
{
    int currentMenuChoice = 0;
    
    public ArrayList<MenuChoice> menu;
    
    public java.util.List<KeyListener> getKeyListeners()
    {
        LinkedList<KeyListener> l = new LinkedList<KeyListener>();
        l.add(this);
        return l;
    }
    
    public OptionsMenuScreen(GamePanel gp)
    {
        container = gp;
        MenuChoice choice1 = new MenuChoice("option 1");
        MenuChoice choice2 = new MenuChoice("option 2");
        MenuChoice choice3 = new MenuChoice("back");
        
        menu = new ArrayList<MenuChoice>(0);
        menu.add(choice1);
        menu.add(choice2);
        menu.add(choice3);
        
        menu.get(currentMenuChoice).selected = true;
    }

    void drawScreen(Graphics g)
    {
        for (int i = 0; i < menu.size(); i++)
        {
            MenuChoice mc = menu.get(i);
            if (mc.selected)
            {
                int stringWidth = g.getFontMetrics().stringWidth(menu.get(i).text);
                int stringHeight = g.getFontMetrics().getHeight();
                g.drawRect(20, i*75 + 25 - stringHeight - 5, stringWidth + 10, stringHeight + 10);
                
            }
            g.drawString(menu.get(i).text, 25, i*75 + 25);
            
        }
    }
    
    void menuDown()
    {
        menu.get(currentMenuChoice).selected = false;
        if (currentMenuChoice + 1 != menu.size())
            currentMenuChoice++;
        menu.get(currentMenuChoice).selected = true;
    }
    
    void menuUp()
    {
        menu.get(currentMenuChoice).selected = false;
        if (currentMenuChoice != 0)
            currentMenuChoice--;
        menu.get(currentMenuChoice).selected = true;
    }
    
    void selectChoice()
    {
        String currentSelected = menu.get(currentMenuChoice).text;
        if (currentSelected.equals("back"))
        {
            container.switchScreens(new MainMenuScreen(container));
        }
    }
    
    public void keyReleased(KeyEvent e){};
    public void keyTyped(KeyEvent e){};
    public void keyPressed(KeyEvent e)
    {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_DOWN)
        {
            this.menuDown();
        }
        else if (keyCode == KeyEvent.VK_UP)
        {
            this.menuUp();
        }
        else if (keyCode == KeyEvent.VK_ENTER)
        {
            this.selectChoice();
        }
    }
    
    public void run(){};
}
