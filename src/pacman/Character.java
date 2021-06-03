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
}
