package pacman;

import javax.swing.*;
import java.awt.*;

/**
 * Pacman class
 */
public class Pacman extends Character {

    private final int PACMAN_SPEED = 5;
    private int numberOfLives;
    private final static Image pacmanImage = new ImageIcon("media/pacman.gif").getImage();

    /**
     * Pacman constructor
     * @param mazeData array storing maze data
     * @param FIELD_SIZE field size in pixels
     * @param WIDTH maze width
     */
    public Pacman(short[] mazeData, int FIELD_SIZE, int WIDTH) {
        loadImages();
        setMoveVector(0, 0);
        countSpawnCoordinates(mazeData, FIELD_SIZE, WIDTH);
        numberOfLives = 3;
    }

    /**
     * Method used to load Pacman images from files
     */
    @Override
    protected void loadImages() {
        up = new ImageIcon("media/pacman-up.gif").getImage();
        down = new ImageIcon("media/pacman-down.gif").getImage();
        left = new ImageIcon("media/pacman-left.gif").getImage();
        right = new ImageIcon("media/pacman-right.gif").getImage();
        actual = right;
    }


    /**
     * Method used to respawn Pacman when is eaten
     * @param mazeData array storing maze data
     * @param FIELD_SIZE field size in pixels
     * @param WIDTH maze width
     */
    public void respawnPacman(short[] mazeData, int FIELD_SIZE, int WIDTH) {
        setMoveVector(0, 0);
        countSpawnCoordinates(mazeData, FIELD_SIZE, WIDTH);
        decreaseLives();
    }

    /**
     * Method checking if Pacman is still alive
     * @return is Pacman alive
     */
    public Boolean isPacmanDead() {
        return getNumberOfLives() == 0;
    }

    /**
     * Method used to decrease number of Pacman lives
     */
    private void decreaseLives() {
        numberOfLives--;
    }

    /**
     * @return number of lives
     */
    public int getNumberOfLives() {
        return numberOfLives;
    }

    /**
     * @return actual Pacman image
     */
    public Image getPacmanImage() {
        return pacmanImage;
    }

    /**
     * Method stopping Pacman move if hits blue wall
     * @param mazeData array storing maze data
     */
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

    /**
     * Method used to find pacman spawn place
     * @param mazeData array storing maze data
     * @param FIELD_SIZE field size in pixels
     * @param WIDTH maze width
     */
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

    /**
     * Method used to make move
     * @param actualMoveVector actual move vector
     * @param mazeData array storing maze data
     */
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

    /**
     * @param x horizontal coordinate of object
     * @param y vertical coordinate of object
     * @return is collision
     */
    public Boolean isCollision(int x, int y) {
        int positionInMazeX = x / 30;
        int positionInMazeY = y / 30;
        int copyOfArrayPlace = positionInMazeY * 28 + positionInMazeX;
        return arrayPlace == copyOfArrayPlace;
    }
}
