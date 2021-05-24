package pacman;

import javax.swing.*;
import java.awt.*;

public class Pacman {

    private Image up, down, left, right, actual;
    public int actualX;
    public int actualY;
    private int pacmanDX;
    private int pacmanDY;

    public Pacman(short[] mazeData, int FIELD_SIZE, int WIDTH) {
        up = new ImageIcon("media/pacman-up-resize.gif").getImage();
        down = new ImageIcon("media/pacman-down-resize.gif").getImage();
        left = new ImageIcon("media/pacman-left-resize.gif").getImage();
        right = new ImageIcon("media/pacman-right-resize.gif").getImage();
        actual = right;
        setMoveVector(0, 0);
        countPacmanSpawnCoordinates(mazeData, FIELD_SIZE, WIDTH);
    }

    public void turnUp() {
        actual = this.up;
    }

    public void turnDown() {
        actual = this.down;
    }

    public void turnRight() {
        actual = this.right;
    }

    public void turnLeft() {
        actual = this.left;
    }

    public Image getImage() {
        return actual;
    }

    public void setMoveVector(int offsetX, int offsetY) {
        pacmanDX = offsetX;
        pacmanDY = offsetY;
    }

    public void updateCoordinates() {
        actualX += pacmanDX;
        actualY += pacmanDY;
    }

    private void countPacmanSpawnCoordinates(short[] mazeData, int FIELD_SIZE, int WIDTH) {
        int pacmanSpawnIndex = 0;
        for (int i = 0; i < mazeData.length; i++) {
            if (mazeData[i] == 5)
                pacmanSpawnIndex = i;
        }
        int cols = pacmanSpawnIndex / WIDTH;
        int rows = pacmanSpawnIndex - cols * WIDTH;
        actualX = rows * FIELD_SIZE - 15;
        actualY = cols * FIELD_SIZE - 3;
    }
}
