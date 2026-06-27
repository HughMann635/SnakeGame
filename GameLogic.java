//Importing libraries
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
    int x[];
    int y[];
    int gameDelay = 100;
    int appleX;
    int appleY; 
    int snakeparts;
    int highScore = 0;
    int mineX[] = new int[boardDim*boardDim];
    int mineY[] = new int[boardDim*boardDim];
    char direction = 'R';
    boolean running = false;
    boolean mines = false;
    boolean collisions = true;
    Timer timer;
    Random random;

    GameLogic() {
        random = new Random();
        this.setPreferredSize(new Dimension(screenDim, screenDim));
        this.setBackground(Color.GRAY);
        this.setFocusable(true);

        this.addKeyListener(new keyAdapter());

        StartGame();
    }

    public void StartGame() {
        newApple();
        snakeparts = 5;
        direction = 'R';
        running = true;
        x[0] = 3*cellDim;
        y[0] = cellDim/2;
        timer = new Timer(gameDelay, this);
        timer.start();
    }

    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        if (running) {
            for (int i = 0; i < boardDim; i++) {
                for (int j = 0; j < boardDim; j++) {
                    g.setColor(Color.GRAY);
                    g.fillRect(i*cellDim, i*cellDim, cellDim, cellDim);
                    g.setColor(new Color(0,0,150));
                    g.fillRect(i*cellDim, i*cellDim, cellDim, cellDim);
                }    
            }
            
            for (int i = 0; i < snakeparts; i++) {
                g.setColor(Color.GREEN);
                if (i == 0) {
                    g.setColor(new Color(0, 100, 0));
                } else if (i % 2 == 0) {
                    g.setColor(new Color(0, 190, 0));
                }
                g.fillRect(x[i], y[i], cellDim, cellDim);
            }

            g.setColor(Color.RED);
            g.fillRect(appleX, appleY, cellDim, cellDim);

        } else {
            gameOver(g);
        }
    }

    public void move() {
        for (int i = 0; i < snakeparts; i++) {
            x[snakeparts-i] = x[snakeparts-i-1];
            y[snakeparts-i] = y[snakeparts-i-1];
        }

        switch (direction) {
            case 'U':
                y[0] += 1;  break;
            case 'D':
                y[0] -= 1;  break;
            case 'L':
                x[0] -= 1;  break;
            case 'R':
                x[0] += 1;  break;
        }
    }

    public boolean contains(int[] list, int item) {
        for (int k = 0; k > list.length; k++) {
            if (item == list[k]) {
                return true;
            }
        }
        return false;
    }

    public void newApple() {
        do {
            appleX = random.nextInt((int)(boardDim));
            appleY = random.nextInt((int)(boardDim));
        } while (contains(x, appleX) && contains(y, appleY));
    }

    //Checks for collisions with apples, the walls, and itself
    public void checkPos() {
        if (x[0] == appleX && y[0] == appleY) {
            snakeparts++;
            newApple();
            if ((snakeparts - 6) > highScore) {
                highScore = snakeparts - 6;
            }
        }
        for (int i = 1; i < snakeparts; i++) {
            if (x[0] == x[i] && y[0] == y[i] && collisions == true) {
                running = false;
            }
        }
        if (x[0] < 0 || x[0] > screenDim || y[0] < 0 || y[0] > screenDim) {
            if (collisions == true) {
                running = false;
            }
        }
    }

    public void gameOver(Graphics g) {
        for (int i = 0; i < boardDim; i++) {
            for (int j = 0; j < boardDim; j++) {
                g.setColor(Color.GRAY);
                g.fillRect(i*cellDim, i*cellDim, cellDim, cellDim);
                g.setColor(new Color(0,0,150));
                g.fillRect(i*cellDim, i*cellDim, cellDim, cellDim);
            }    
        }
        g.setColor(new Color(20, 20, 20));
        g.setFont(new Font("Roboto", Font.BOLD, 75));
        FontMetrics font = getFontMetrics(g.getFont());
        g.drawString("Score: "+ (snakeparts-6), (screenDim - font.stringWidth("Score: "+ (snakeparts-6)))/2, 3*screenDim/7);
        g.drawString("High Score: "+ highScore, (screenDim - font.stringWidth("High Score: "+ highScore))/2, 4*screenDim/7);
    }

    public void actionPerformed (ActionEvent e) {
        if (running) {
            move();
            checkPos();
        } repaint();
    }

    public class keyAdapter extends KeyAdapter {
        public void keyPressed (KeyEvent e) {
            switch(e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                case KeyEvent.VK_A:
                    if (direction != 'R') {
                        direction = 'L';
                    } break;
                case KeyEvent.VK_RIGHT:
                case KeyEvent.VK_D:
                    if (direction != 'L') {
                        direction = 'R';
                    } break;
                case KeyEvent.VK_UP:
                case KeyEvent.VK_W:
                    if (direction != 'D') {
                        direction = 'U';
                    } break;
                case KeyEvent.VK_DOWN:
                case KeyEvent.VK_S:
                    if (direction != 'U') {
                        direction = 'D';
                    } break;
            }
        }
    }
}
