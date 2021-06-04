package pacman;

import java.awt.*;

abstract class Character {
    public int actualX;
    public int actualY;
    protected int moveDX;
    protected int moveDY;
    protected Image up;
    protected Image down;
    protected Image left;
    protected Image right;
    protected Image actual;
    private final int GHOST_SPEED = 5;
    public int arrayPlace;
    public Boolean isInHouse = true;
    private Boolean isLeftSide = true;

    abstract void loadImages();
    abstract void countSpawnCoordinates(short[] mazeData, int FIELD_SIZE, int WIDTH);

    protected void setMoveVector(int offsetX, int offsetY) {
        moveDX = offsetX;
        moveDY = offsetY;
    }

    protected void updateCoordinates() {
        actualX += moveDX * GHOST_SPEED;
        actualY += moveDY * GHOST_SPEED;
        int positionInMazeX = ( actualX )/ 30;
        int positionInMazeY = ( actualY )/ 30;
        arrayPlace = positionInMazeY * 28 + positionInMazeX;
    }

    protected Boolean canMoveUp(short[] mazeData) {
        return arrayPlace - 28 > 0 && mazeData[arrayPlace - 28] != 0;
    }

    protected Boolean canMoveDown(short[] mazeData) {
        return arrayPlace + 28 > 0 && mazeData[arrayPlace + 28] != 0 && mazeData[arrayPlace + 28] != 4;
    }

    protected Boolean canMoveLeft(short[] mazeData) {
        return arrayPlace - 1 > 0 && mazeData[arrayPlace - 1] != 0;
    }

    protected Boolean canMoveRight(short[] mazeData) {
        return arrayPlace + 1 > 0 && mazeData[arrayPlace + 1] != 0;
    }

    protected Boolean isHorizontalMoveValid() {
        return actualX % 30 == 0;
    }

    protected Boolean isVerticalMoveValid() {
        return actualY % 30 == 0;
    }

    protected void turnUp() {
        actual = this.up;
    }

    protected void turnDown() {
        actual = this.down;
    }

    protected void turnRight() {
        actual = this.right;
    }

    protected void turnLeft() {
        actual = this.left;
    }

    public Image getImage() {
        return actual;
    }

    public void printNeighbours(short[] mazeData) {
        System.out.println(arrayPlace);
        System.out.println((arrayPlace -28 )+ " actual  Up " + mazeData[arrayPlace - 28]);
        System.out.println("Down " + mazeData[arrayPlace + 28]);
        System.out.println("Left " + mazeData[arrayPlace - 1]);
        System.out.println("Right " + mazeData[arrayPlace + 1]);
    }

    public void goUp(short[] mazeData) {
        if (isHorizontalMoveValid() && canMoveUp(mazeData)) {
            setMoveVector(0, -1);
            turnUp();
            System.out.println("Going up");
        }
        updateCoordinates();
    }

    public void goDown(short[] mazeData) {
        if (isHorizontalMoveValid() && canMoveDown(mazeData)) {
            setMoveVector(0, 1);
            System.out.println("Going down");
            turnDown();
        }
        updateCoordinates();
    }

    public void goLeft(short[] mazeData) {
        if (isVerticalMoveValid() && canMoveLeft(mazeData)) {
            setMoveVector(-1, 0);
            System.out.println("Going left");
            turnLeft();
        }
        updateCoordinates();
    }

    public void goRight(short[] mazeData) {
        if (isVerticalMoveValid() && canMoveRight(mazeData)) {
            setMoveVector(1, 0);
            System.out.println("Going right");
            turnRight();
        }
        updateCoordinates();
    }

    private Boolean checkIfGhostCrossedWhiteLine(short[] mazeData) {
        return (mazeData[arrayPlace + 28] == 4 && actualY % 30 == 0);
    }

    public void goOutOfHouse(short[] mazeData) {
        if (checkIfGhostCrossedWhiteLine(mazeData)) {
            isInHouse = false;
            return;
        }
        if (mazeData[arrayPlace - 28] != 0) {
            goUp(mazeData);
            return;
        }
        if (mazeData[arrayPlace + 1] != 0 && isLeftSide) {
            goRight(mazeData);
        }
        else {
            isLeftSide = false;
        }
        if (mazeData[arrayPlace - 1] != 0 && !isLeftSide) {
            goLeft(mazeData);
        }
    }
}
