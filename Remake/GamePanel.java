
package Remake;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.applet.*;

public class GamePanel extends JPanel implements Runnable
{
    GameScreen currentScreen;
    GameFrame containerFrame;
    
    public GamePanel(GameFrame gf)
    {
        this.containerFrame = gf;
        this.init();
        this.start();
    }
    
    public void switchScreens(GameScreen gs)
    {
        removeKeyListeners();
        currentScreen = gs;
        addKeyListeners(gs.getKeyListeners());
    }
    
    void removeKeyListeners()
    {
        KeyListener[] l = containerFrame.getKeyListeners();
        for (KeyListener k : l)
        {
            containerFrame.removeKeyListener(k);
        }
    }
    
    void addKeyListeners(java.util.List<KeyListener> l)
    {
        for (KeyListener k : l)
        {
            containerFrame.addKeyListener(k);
        }
    }
    
    public void run()
    {
        while (true)
        {
            currentScreen.run();
            repaint();
            try
            {
                Thread.sleep(20);
            }
            catch (InterruptedException e)
            {
            }
        }
    }
    
     /**
     * Called by the browser or applet viewer to inform this JApplet that it
     * has been loaded into the system. It is always called before the first 
     * time that the start method is called.
     */
    public void init()
    {
        currentScreen = new MainMenuScreen(this);
        containerFrame.addKeyListener(currentScreen);
    }

    /**
     * Called by the browser or applet viewer to inform this JApplet that it 
     * should start its execution. It is called after the init method and 
     * each time the JApplet is revisited in a Web page. 
     */
    public void start()
    {
        Thread th = new Thread(this);
        th.start();
    }
    
    

    /** 
     * Called by the browser or applet viewer to inform this JApplet that
     * it should stop its execution. It is called when the Web page that
     * contains this JApplet has been replaced by another page, and also
     * just before the JApplet is to be destroyed. 
     */
    public void stop()
    {
        // provide any code that needs to be run when page
        // is replaced by another page or before JApplet is destroyed 
    }

    /**
     * Paint method for applet.
     * 
     * @param  g   the Graphics object for this applet
     */
    public void paint(Graphics g)
    {
        super.paintComponent(g); //clear screen
        currentScreen.drawScreen(g);
    }

    /**
     * Called by the browser or applet viewer to inform this JApplet that it
     * is being reclaimed and that it should destroy any resources that it
     * has allocated. The stop method will always be called before destroy. 
     */
    public void destroy()
    {
        // provide code to be run when JApplet is about to be destroyed.
    }


    /**
     * Returns information about this applet. 
     * An applet should override this method to return a String containing 
     * information about the author, version, and copyright of the JApplet.
     *
     * @return a String representation of information about this JApplet
     */
    public String getAppletInfo()
    {
        // provide information about the applet
        return "Title:   \nAuthor:   \nA simple applet example description. ";
    }


    /**
     * Returns parameter information about this JApplet. 
     * Returns information about the parameters than are understood by this JApplet.
     * An applet should override this method to return an array of Strings 
     * describing these parameters. 
     * Each element of the array should be a set of three Strings containing 
     * the name, the type, and a description.
     *
     * @return a String[] representation of parameter information about this JApplet
     */
    public String[][] getParameterInfo()
    {
        // provide parameter information about the applet
        String paramInfo[][] = {
                 {"firstParameter",    "1-10",    "description of first parameter"},
                 {"status", "boolean", "description of second parameter"},
                 {"images",   "url",     "description of third parameter"}
        };
        return paramInfo;
    }
}
