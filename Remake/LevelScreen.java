package Remake;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
public class LevelScreen extends GameScreen
{
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
        gameHeight = GameConstants.GAME_HEIGHT;
        gameWidth = GameConstants.GAME_WIDTH;
        initSolids();
        p = new Player();
    }
    
    void initSolids()
    {
        Solid floor = new Box(0, gameHeight - 100, gameWidth, 10);
        Solid box = new Box(200, gameHeight - 250, gameWidth, 10);
        Solid leftWall = new Box(0,0,3, gameHeight);
        Solid rightWall = new Box(gameWidth - 3, 0, 3, gameHeight);
        
        GameConstants.solidMap.addSolid(floor);   
        GameConstants.solidMap.addSolid(box);
        GameConstants.solidMap.addSolid(rightWall);
        GameConstants.solidMap.addSolid(leftWall);
        
        solids = new ArrayList<Solid>(0);
        solids.add(floor);
        solids.add(box);
        solids.add(leftWall);
        solids.add(rightWall);
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
