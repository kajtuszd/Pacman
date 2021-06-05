package pacman;

import javax.swing.*;
import java.awt.*;

public class Pacman extends Character {

    private final int PACMAN_SPEED = 5;
    private int numberOfLives;
    private final static Image pacmanImage = new ImageIcon("media/pacman.gif").getImage();

    public Pacman(short[] mazeData, int FIELD_SIZE, int WIDTH) {
        loadImages();
        setMoveVector(0, 0);
        countSpawnCoordinates(mazeData, FIELD_SIZE, WIDTH);
        numberOfLives = 3;
    }

    @Override
    protected void loadImages() {
        up = new ImageIcon("media/pacman-up.gif").getImage();
        down = new ImageIcon("media/pacman-down.gif").getImage();
        left = new ImageIcon("media/pacman-left.gif").getImage();
        right = new ImageIcon("media/pacman-right.gif").getImage();
        actual = right;
    }

    public void respawnPacman(short[] mazeData, int FIELD_SIZE, int WIDTH) {
        setMoveVector(0, 0);
        countSpawnCoordinates(mazeData, FIELD_SIZE, WIDTH);
        decreaseLives();
    }

    public Boolean isPacmanDead() {
        return getNumberOfLives() == 0;
    }

    private void decreaseLives() {
        numberOfLives--;
    }

    public int getNumberOfLives() {
        return numberOfLives;
    }

    public Image getPacmanImage() {
        return pacmanImage;
    }

    private void stopPacmanIfMeetsWall(short[] mazeData) {
        int copyOfActualX = actualX + moveDX * PACMAN_SPEED;
        int copyOfActualY = actualY + moveDY * PACMAN_SPEED;
        /* moving down and right forces 30-60px addition in order to watch for the next block */
        if (moveDX == 1 && moveDY == 0) {
            copyOfActualX = actualX + 30;
        }
        if (moveDX == 0 && moveDY == 1) {
            copyOfActualY = actualY + 30;
        }
        int positionInMazeX = (copyOfActualX )/ 30;
        int positionInMazeY = (copyOfActualY )/ 30;
        int copyOfArrayPlace = positionInMazeY * 28 + positionInMazeX;
        if (mazeData[copyOfArrayPlace] == 0) {
            setMoveVector(0, 0);
        }
    }

    @Override
    protected void countSpawnCoordinates(short[] mazeData, int FIELD_SIZE, int WIDTH) {
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

    public Boolean isCollision(int x, int y) {
        int positionInMazeX = x / 30;
        int positionInMazeY = y / 30;
        int copyOfArrayPlace = positionInMazeY * 28 + positionInMazeX;
        return arrayPlace == copyOfArrayPlace;
    }
}
