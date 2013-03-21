 
package Remake;

import javax.swing.JFrame;

public class GameFrame extends JFrame {

    public GameFrame() {
        initializeGameConstants();
        add(new GamePanel(this));
        setTitle("REMAKE-GAME");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500,500);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    static void initializeGameConstants()
    {
        GameConstants.GAME_HEIGHT = 500;
        GameConstants.GAME_WIDTH = 500;
        GameConstants.solidMap = new SolidMap(500,500);
    }

    public static void main(String[] args) {
        new GameFrame();
    }
}
