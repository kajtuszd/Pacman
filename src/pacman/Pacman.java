package pacman;

import javax.swing.*;
import java.awt.*;

public class Pacman {

    private final Image up;
    private final Image down;
    private final Image left;
    private final Image right;
    private Image actual;
    public int actualX;
    public int actualY;
    private int pacmanDX;
    private int pacmanDY;
    private final int PACMAN_SPEED = 5;
    public int arrayPlace;

    public Pacman(short[] mazeData, int FIELD_SIZE, int WIDTH) {
        up = new ImageIcon("media/pacman-up.gif").getImage();
        down = new ImageIcon("media/pacman-down.gif").getImage();
        left = new ImageIcon("media/pacman-left.gif").getImage();
        right = new ImageIcon("media/pacman-right.gif").getImage();
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
        int positionInMazeX = ( actualX )/ 30;
        int positionInMazeY = ( actualY )/ 30;
        arrayPlace = positionInMazeY * 28 + positionInMazeX;
    }

    private void stopPacmanIfMeetsWall(short[] mazeData) {
        int copyOfActualX = actualX + pacmanDX * PACMAN_SPEED;
        int copyOfActualY = actualY + pacmanDY * PACMAN_SPEED;
        /* moving down and right forces 30-60px addition in order to watch for the next block */
        if (pacmanDX == 1 && pacmanDY == 0) {
            copyOfActualX = actualX + 30;
        }
        if (pacmanDX == 0 && pacmanDY == 1) {
            copyOfActualY = actualY + 30;
        }
        int positionInMazeX = (copyOfActualX )/ 30;
        int positionInMazeY = (copyOfActualY )/ 30;
        int copyOfArrayPlace = positionInMazeY * 28 + positionInMazeX;
        if (mazeData[copyOfArrayPlace] == 0) {
            setMoveVector(0, 0);
        }
    }

    private void goThroughTunnelAndChangeSide() {
        if (actualX / 30 == 27) {
            actualX -= 26 * 30;
        }
        if (actualX / 30 == 0) {
            actualX += 26 * 30;
        }
        int positionInMazeX = ( actualX )/ 30;
        int positionInMazeY = ( actualY )/ 30;
        arrayPlace = positionInMazeY * 28 + positionInMazeX;
    }

    private Boolean canMoveUp(short[] mazeData) {
        return arrayPlace - 28 > 0 && mazeData[arrayPlace - 28] != 0;
    }

    private Boolean canMoveDown(short[] mazeData) {
        return arrayPlace + 28 > 0 && mazeData[arrayPlace + 28] != 0 && mazeData[arrayPlace + 28] != 4;
    }

    private Boolean canMoveLeft(short[] mazeData) {
        return arrayPlace - 1 > 0 && mazeData[arrayPlace - 1] != 0;
    }

    private Boolean canMoveRight(short[] mazeData) {
        return arrayPlace + 1 > 0 && mazeData[arrayPlace + 1] != 0;
    }

    private Boolean isHorizontalMoveValid() {
        return actualX % 30 == 0;
    }

    private Boolean isVerticalMoveValid() {
        return actualY % 30 == 0;
    }

    private void countPacmanSpawnCoordinates(short[] mazeData, int FIELD_SIZE, int WIDTH) {
        int pacmanSpawnIndex = 0;
        for (int i = 0; i < mazeData.length; i++) {
            if (mazeData[i] == 5)
                pacmanSpawnIndex = i;
        }
        int cols = pacmanSpawnIndex / WIDTH;
        int rows = pacmanSpawnIndex - cols * WIDTH;
        actualX = rows * FIELD_SIZE;
        actualY = cols * FIELD_SIZE;
    }

    public void makeMove(int[] actualMoveVector, short[] mazeData) {
        if (actualMoveVector[0] == 0 && actualMoveVector[1] == -1) {
            if (isHorizontalMoveValid() && canMoveUp(mazeData)) {
                setMoveVector(0, -1);
                turnUp();
            }
        }
        else if (actualMoveVector[0] == 0 && actualMoveVector[1] == 1) {
            if (isHorizontalMoveValid() && canMoveDown(mazeData)) {
                setMoveVector(0, 1);
                turnDown();
            }
        }
        else if (actualMoveVector[0] == -1 && actualMoveVector[1] == 0) {
            if (isVerticalMoveValid() && canMoveLeft(mazeData)) {
                setMoveVector(-1, 0);
                turnLeft();
            }
        }
        else if (actualMoveVector[0] == 1 && actualMoveVector[1] == 0) {
            if (isVerticalMoveValid() && canMoveRight(mazeData)) {
                setMoveVector(1, 0);
                turnRight();
            }
        }
        goThroughTunnelAndChangeSide();
        updateCoordinates();
        stopPacmanIfMeetsWall(mazeData);
    }
}
