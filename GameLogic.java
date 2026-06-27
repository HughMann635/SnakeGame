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
    int x[] = new int[boardDim*boardDim];
    int y[] = new int[boardDim*boardDim];
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
    int buttonwidth = 60;
    int buttonheight = 40;
    Timer timer;
    Random random;

    //Array containing the dimensions of the buttons featured on the main menu screen
    //Top row: Speed adjustment (slow, medium, fast)
    //Middle row: Board size adjustment (small, medium, large)
    //Bottom row: Modifiers (toggle mines, toggle collisions, reset modifiers)
    //At the very bottom is the Start button
    int[][] buttons = {
        {200, 3*screenDim/8}, {330, 3*screenDim/8}, {460, 3*screenDim/8},
        {200, 4*screenDim/8}, {330, 4*screenDim/8}, {460, 4*screenDim/8},    
        {200, 5*screenDim/8}, {330, 5*screenDim/8}, {460, 5*screenDim/8},
        {35, 6*screenDim/8}
    };

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
        x[0] = 3;
        y[0] = boardDim/2;
        timer = new Timer(gameDelay, this);
        timer.start();
    }

    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        if (running) {
            for (int i = 0; i < boardDim; i++) {
                for (int j = 0; j < boardDim; j++) {
                    g.setColor(Color.GRAY);
                    g.fillRect(i*cellDim, j*cellDim, cellDim, cellDim);
                    g.setColor(new Color(0,0,150));
                    g.drawRect(i*cellDim, j*cellDim, cellDim, cellDim);
                }    
            }
            
            for (int i = 0; i < snakeparts; i++) {
                g.setColor(Color.GREEN);
                if (i == 0) {
                    g.setColor(new Color(0, 100, 0));
                } else if (i % 2 == 0) {
                    g.setColor(new Color(0, 190, 0));
                }
                g.fillRect(x[i]*cellDim, y[i]*cellDim, cellDim, cellDim);
            }

            g.setColor(Color.RED);
            g.fillRect(appleX*cellDim, appleY*cellDim, cellDim, cellDim);

        g.setFont(new Font("Roboto", Font.BOLD, 40));
        FontMetrics font = getFontMetrics(g.getFont());
        g.drawString("Score: "+ (snakeparts-5), (screenDim - font.stringWidth("Score: "+ (snakeparts-5)))/2, screenDim/12);

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
                y[0] -= 1;  break;
            case 'D':
                y[0] += 1;  break;
            case 'L':
                x[0] -= 1;  break;
            case 'R':
                x[0] += 1;  break;
            case 'X':
                gameOver(getGraphics()); break;
        }
    }

    public boolean contains(int[] list, int item) {
        for (int k = 0; k < list.length; k++) {
            if (list[k] == item) {
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
            if ((snakeparts - 5) > highScore) {
                highScore = snakeparts - 5;
            }
        }
        for (int i = 1; i < snakeparts; i++) {
            if (x[0] == x[i] && y[0] == y[i] && collisions == true) {
                running = false;
            }
        }
        if (x[0] < 0 || x[0] >= boardDim || y[0] < 0 || y[0] >= boardDim) {
            if (collisions == true) {
                running = false;
            }
        }
    }

    public void gameOver(Graphics g) {
        for (int i = 0; i < boardDim; i++) {
            for (int j = 0; j < boardDim; j++) {
                g.setColor(Color.GRAY);
                g.fillRect(i*cellDim, j*cellDim, cellDim, cellDim);
                g.setColor(new Color(0,0,150));
                g.drawRect(i*cellDim, j*cellDim, cellDim, cellDim);
            }    
        }
        g.setColor(new Color(20, 20, 20));
        g.setFont(new Font("Roboto", Font.BOLD, 75));
        FontMetrics font = getFontMetrics(g.getFont());
        g.drawString("Score: "+ (snakeparts-5), (screenDim - font.stringWidth("Score: "+ (snakeparts-5)))/2, 1*screenDim/7);
        g.drawString("High Score: "+ highScore, (screenDim - font.stringWidth("High Score: "+ highScore))/2, 2*screenDim/7);

        for (int i = 0; i < 10; i++) {
            switch (i) {
                case 0: case 1: case 2:
                    g.setColor(new Color(255,255,0)); break;
                case 3: case 4: case 5:
                    g.setColor(new Color(0,255,0)); break;
                case 6: case 7: case 8:
                    g.setColor(new Color(255,0,0)); break;
                case 9:
                    g.setColor(new Color(100,100,100)); break; 
            }
            if (i == 9) {
                g.fillRect(buttons[i][0], buttons[i][1], buttonwidth+40, buttonheight+10); 
            } else { 
                g.fillRect(buttons[i][0], buttons[i][1], buttonwidth, buttonheight); 
            }
        }
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
                case KeyEvent.VK_R:
                    direction = 'X';
            }
        }
    }
}
