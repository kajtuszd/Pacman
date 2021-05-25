package pacman;

import javax.swing.*;
import java.awt.*;

public class Pacman {

    private Image up, down, left, right, actual;
    public int actualX;
    public int actualY;
    private int pacmanDX;
    private int pacmanDY;
    private int PACMAN_SPEED = 5;
    private int OFFSET_X = 15;
    private int OFFSET_Y = 3;

    public Pacman(short[] mazeData, int FIELD_SIZE, int WIDTH) {
        up = new ImageIcon("media/pacman-up-resize.gif").getImage();
        down = new ImageIcon("media/pacman-down-resize.gif").getImage();
        left = new ImageIcon("media/pacman-left-resize.gif").getImage();
        right = new ImageIcon("media/pacman-right-resize.gif").getImage();
        actual = right;
        setMoveVector(0, 0);
        countPacmanSpawnCoordinates(mazeData, FIELD_SIZE, WIDTH);
    }

    private void turnUp() {
        actual = this.up;
    }

    private void turnDown() {
        actual = this.down;
    }

    private void turnRight() {
        actual = this.right;
    }

    private void turnLeft() {
        actual = this.left;
    }

    public Image getImage() {
        return actual;
    }

    private void setMoveVector(int offsetX, int offsetY) {
        pacmanDX = offsetX;
        pacmanDY = offsetY;
    }

    private void updateCoordinates() {
        actualX += pacmanDX * PACMAN_SPEED;
        actualY += pacmanDY * PACMAN_SPEED;
    }

    private Boolean isHorizontalMoveValid() {
        return actualX % 30 == 0;
    }

    private Boolean isVerticalMoveValid() {
        return actualY % 30 == 27;
    }

    private void countPacmanSpawnCoordinates(short[] mazeData, int FIELD_SIZE, int WIDTH) {
        int pacmanSpawnIndex = 0;
        for (int i = 0; i < mazeData.length; i++) {
            if (mazeData[i] == 5)
                pacmanSpawnIndex = i;
        }
        int cols = pacmanSpawnIndex / WIDTH;
        int rows = pacmanSpawnIndex - cols * WIDTH;
        actualX = rows * FIELD_SIZE - OFFSET_X;
        actualY = cols * FIELD_SIZE - OFFSET_Y;
    }

    public void makeMove(int[] actualMoveVector)
    {
        if (actualMoveVector[0] == 0 && actualMoveVector[1] == -1)
        {
            if (isHorizontalMoveValid()){
                setMoveVector(0, -1);
                turnUp();
            }
        }
        else if (actualMoveVector[0] == 0 && actualMoveVector[1] == 1)
        {
            if (isHorizontalMoveValid()){
                setMoveVector(0, 1);
                turnDown();
            }
        }
        else if (actualMoveVector[0] == -1 && actualMoveVector[1] == 0)
        {
            if (isVerticalMoveValid()){
                setMoveVector(-1, 0);
                turnLeft();
            }
        }
        else if (actualMoveVector[0] == 1 && actualMoveVector[1] == 0)
        {
            if (isVerticalMoveValid()){
                setMoveVector(1, 0);
                turnRight();
            }
        }
        updateCoordinates();
    }
}
