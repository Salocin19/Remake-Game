import java.awt.*;
import java.awt.event.*;
import java.util.*;
public abstract class GameScreen implements KeyListener
{
    GamePanel container;
    abstract void drawScreen(Graphics g);
    abstract void run();
    abstract java.util.List<KeyListener> getKeyListeners();
}
