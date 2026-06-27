import javax.swing.JFrame;

public class GameWindow extends JFrame {
    GameWindow() {
        this.add(new GameLogic());
        this.setTitle("Snake");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}