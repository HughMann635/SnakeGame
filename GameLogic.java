//Importing libraries
import javax.swing.Action;
import javax.swing.JPanel;
import java.awt.*;
import java.util.Random;
import javax.swing.Timer;
import java.awt.event.*;

public class GameLogic extends JPanel implements ActionListener {
    //Declaring needed variables
    int boardDim = 15;
    int screenDim = 600;
    int cellDim = screenDim/boardDim;
    int gameDelay = 100;
    int appleX;
    int appleY; 
    int snakeparts;
    int highScore = 0;
    int width;
    int height;
    int mineX = new int[boardDim*boardDim];
    int mineY = new int[boardDim*boardDim];
    char direction = 'R';
    boolean running = false;
    boolean mines = false;
    boolean collisions = true;
    Timer timer;
    Random random;

    GamePanel() {

    }

    public void StartGame() {

    }

    public void paintComponent (Graphics g) {

    }

    public void move() {

    }

    public void newApple() {

    }

    public void checkPos() {

    }

    public void gameOver() {

    }

    public void actionRunner() {

    }

    public class keyAdapter extends KeyAdapter {

    }
}
