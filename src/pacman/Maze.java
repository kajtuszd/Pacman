package pacman;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Maze extends JPanel {

    private Pacman pacman;
    private final int WIDTH = 28;
    private final int HEIGHT = 31;
    private final int FIELD_SIZE = 30;
    private final int SCREEN_WIDTH = WIDTH * FIELD_SIZE;
    private final int SCREEN_HEIGHT = HEIGHT * FIELD_SIZE;
    private static int PACMAN_SPAWN_ROW;
    private static int PACMAN_SPAWN_COL;
    private int pacmanDX;
    private int pacmanDY;
    private int pacmanX;
    private int pacmanY;

    public Maze() {
        countPacmanSpawnCoords();
        pacman = new Pacman();
        pacmanDX = 0;
        pacmanDY = 0;
        pacmanX = PACMAN_SPAWN_ROW;
        pacmanY = PACMAN_SPAWN_COL;
        addKeyListener(new PacmanAdapter());
        setFocusable(true);
    }

    private void countPacmanSpawnCoords() {
        int pacmanSpawnIndex = 0;
        for (int i = 0; i < mazeData.length; i++) {
            if (mazeData[i] == 5)
                pacmanSpawnIndex = i;
        }
        int cols = pacmanSpawnIndex / WIDTH;
        int rows = pacmanSpawnIndex - cols * WIDTH;
        PACMAN_SPAWN_ROW = rows * FIELD_SIZE - 15;
        PACMAN_SPAWN_COL = cols * FIELD_SIZE - 3;
    }

    /*
    * 0 - blue wall
    * 1 - black empty path
    * 2 - path with food
    * 3 - spawn place for ghosts
    * 4 - white line
    * 5 - spawn place for Pacman
    * */
    private short[] mazeData = {
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0,
            0, 2, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 2, 0, 0, 2, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 2, 0,
            0, 2, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 2, 0, 0, 2, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 2, 0,
            0, 2, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 2, 0, 0, 2, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 2, 0,
            0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0,
            0, 2, 0, 0, 0, 0, 2, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 2, 0, 0, 0, 0, 2, 0,
            0, 2, 0, 0, 0, 0, 2, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 2, 0, 0, 0, 0, 2, 0,
            0, 2, 2, 2, 2, 2, 2, 0, 0, 2, 2, 2, 2, 0, 0, 2, 2, 2, 2, 0, 0, 2, 2, 2, 2, 2, 2, 0,
            0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 2, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 2, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 2, 0, 0, 1, 0, 0, 0, 4, 4, 0, 0, 0, 1, 0, 0, 2, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 2, 0, 0, 1, 0, 3, 3, 3, 3, 3, 3, 0, 1, 0, 0, 2, 0, 0, 0, 0, 0, 0,
            1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 0, 3, 3, 3, 3, 3, 3, 0, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1,
            0, 0, 0, 0, 0, 0, 2, 0, 0, 1, 0, 3, 3, 3, 3, 3, 3, 0, 1, 0, 0, 2, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 2, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 2, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 2, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 2, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 2, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 2, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 2, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 2, 0, 0, 0, 0, 0, 0,
            0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0,
            0, 2, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 2, 0, 0, 2, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 2, 0,
            0, 2, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 2, 0, 0, 2, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 2, 0,
            0, 2, 2, 2, 0, 0, 2, 2, 2, 2, 2, 2, 2, 5, 5, 2, 2, 2, 2, 2, 2, 2, 0, 0, 2, 2, 2, 0,
            0, 0, 0, 2, 0, 0, 2, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 2, 0, 0, 2, 0, 0, 0,
            0, 0, 0, 2, 0, 0, 2, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 2, 0, 0, 2, 0, 0, 0,
            0, 2, 2, 2, 2, 2, 2, 0, 0, 2, 2, 2, 2, 0, 0, 2, 2, 2, 2, 0, 0, 2, 2, 2, 2, 2, 2, 0,
            0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0,
            0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0,
            0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
    };

    public void drawMaze(Graphics2D graphics) {
        short i = 0;
        graphics.setStroke(new BasicStroke(5));
        Color blue = new Color(10, 10, 250);
        Color yellow = new Color(255, 179, 0);

        for (int x = 0; x < SCREEN_HEIGHT; x += FIELD_SIZE) {
            for (int y = 0; y < SCREEN_WIDTH; y += FIELD_SIZE) {

                if (mazeData[i] == 0) {
                    graphics.setColor(blue);
                    graphics.fillRect(y, x, FIELD_SIZE*4/5, FIELD_SIZE*4/5);
                }

                if (mazeData[i] == 1 || mazeData[i] == 3 || mazeData[i] == 5) {
                    graphics.setColor(Color.black);
                    graphics.fillRect(y, x, FIELD_SIZE, FIELD_SIZE);
                }

                if (mazeData[i] == 2) {
                    graphics.setColor(Color.black);
                    graphics.fillRect(y, x, FIELD_SIZE, FIELD_SIZE);
                    graphics.setColor(yellow);
                    graphics.fillOval(y + 10 , x + 10, 5, 5);
                }

                if (mazeData[i] == 4) {
                    graphics.setColor(Color.black);
                    graphics.fillRect(y, x, FIELD_SIZE, FIELD_SIZE);
                    graphics.setColor(Color.white);
                    graphics.drawLine(387,370, 447, 370);
                }
                i++;
            }
        }
    }

    public void spawnPacman(Graphics2D graphics, int row, int column) {
        graphics.drawImage(pacman.getImage(), row, column, this);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.black);
        g2d.fillRect(0, 0, 840, 930);
        drawMaze(g2d);
        spawnPacman(g2d, pacmanX, pacmanY);
    }


    class PacmanAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            int pressedKey = e.getKeyCode();
            if (pressedKey == KeyEvent.VK_UP) {
                pacmanDX = 0;
                pacmanDY = -1;
                pacman.turnUp();
            }
            if (pressedKey == KeyEvent.VK_DOWN) {
                pacmanDX = 0;
                pacmanDY = 1;
                pacman.turnDown();
            }
            if (pressedKey == KeyEvent.VK_LEFT) {
                pacmanDX = -1;
                pacmanDY = 0;
                pacman.turnLeft();
            }
            if (pressedKey == KeyEvent.VK_RIGHT) {
                pacmanDX = 1;
                pacmanDY = 0;
                pacman.turnRight();
            }
        }
    }
}
