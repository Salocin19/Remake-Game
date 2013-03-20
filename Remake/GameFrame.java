 
package Remake;

import javax.swing.JFrame;

public class GameFrame extends JFrame {

    public GameFrame() {
        add(new GamePanel(this));
        setTitle("REMAKE-GAME");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500,500);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }
    public static void main(String[] args) {
        new GameFrame();
    }
}
