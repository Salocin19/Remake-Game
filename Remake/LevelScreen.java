package Remake;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
public class LevelScreen extends GameScreen
{
    SolidMap solidMap;

    int gameHeight;
    int gameWidth;
    Player p;
    ArrayList<Solid> solids;
    
    public java.util.List<KeyListener> getKeyListeners()
    {
        LinkedList<KeyListener> l = new LinkedList<KeyListener>();
        l.add(p);
        l.add(this);
        return l;
    }

    public LevelScreen(GamePanel gp)
    {
        HitBoxesMap.initialize();
        
        this.container = gp;
        gameHeight = gp.getHeight();
        gameWidth = gp.getWidth();
        initSolids();
        p = new Player("Sprites/Salocin.png", solidMap);
    }
    
    void initSolids()
    {
        Solid floor = new Box(0, gameHeight - 100, gameWidth, 10);
        Solid box = new Box(200, gameHeight - 250, gameWidth, 10);
        
        solidMap = new SolidMap(gameWidth, gameHeight);
        solidMap.addSolid(floor);   
        solidMap.addSolid(box);
        
        solids = new ArrayList<Solid>(0);
        solids.add(floor);
        solids.add(box);
    }

    public void drawScreen(Graphics g)
    {
        g.setColor(Color.BLACK);
        drawSolids(g);
        p.draw(g);
    }
    
    void drawSolids(Graphics g)
    {
        for (Solid s : solids)
        {
            s.draw(g);
        }
    }
    
    public void keyTyped(KeyEvent e){};
    public void keyReleased(KeyEvent e){};
    public void keyPressed(KeyEvent e)
    {
        int keycode = e.getKeyCode();
        if (keycode == KeyEvent.VK_ESCAPE)
        {
            pause();
        }
    }
    
    public void run()
    {
        p.run();
        
    }
    
    void pause()
    {
        p.pause();
        container.switchScreens(new PauseScreen(container, this));
    }
}
