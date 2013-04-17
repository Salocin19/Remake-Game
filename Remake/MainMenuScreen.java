package Remake;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
public class MainMenuScreen extends GameScreen
{
    int currentMenuChoice = 0;
    
    public ArrayList<MenuChoice> menu;
    
    public java.util.List<KeyListener> getKeyListeners()
    {
        LinkedList<KeyListener> l = new LinkedList<KeyListener>();
        l.add(this);
        return l;
    }
    
    public MainMenuScreen(GamePanel gp)
    {
        container = gp;
        MenuChoice choice1 = new MenuChoice("start");
        MenuChoice choice2 = new MenuChoice("multiplayer");
        MenuChoice choice3 = new MenuChoice("options");
        
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
        if (currentSelected.equals("start"))
        {
            container.switchScreens(new LevelScreen(container));
        }
        else if (currentSelected.equals("multiplayer"))
        {
            //MultiPlayerScreen mp = new MultiPlayerScreen(container);
            EnterNameScreen ens = new EnterNameScreen();
            container.switchScreens(ens);
            //mp.initializeConnections();
        }
        else if (currentSelected.equals("options"))
        {
            container.switchScreens(new OptionsMenuScreen(container));
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
