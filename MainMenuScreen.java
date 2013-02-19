import java.awt.*;
import java.util.*;
public class MainMenuScreen extends GameScreen
{
    class MenuChoice
    {
        public String text;
        
        public MenuChoice(String s)
        {
            text = s;
        }
    }
    
    public ArrayList<MenuChoice> menu;
    public MainMenuScreen()
    {
        MenuChoice choice1 = new MenuChoice("choice1");
        MenuChoice choice2 = new MenuChoice("choice2");
        MenuChoice choice3 = new MenuChoice("choice3");
        
        menu.add(choice1);
        menu.add(choice2);
        menu.add(choice3);
    }

    void drawScreen(Graphics g)
    {
        //g.drawString(menu.size() + "" + " hello", 25, 25);
        g.drawString("yoyoyo",25,25);
        /*for (int i = 0; i < menu.size(); i++)
        {
            g.drawString(menu.get(i).text, 25, i*25 + 25);
        }*/
    }
}
