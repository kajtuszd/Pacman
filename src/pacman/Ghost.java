package pacman;

import java.awt.*;

abstract class Ghost {
    public int actualX;
    public int actualY;
    private int ghostDX;
    private int ghostDY;
    protected Image up;
    protected Image down;
    protected Image left;
    protected Image right;
    protected Image actual;
    private final int GHOST_SPEED = 5;
    public int arrayPlace;

    protected void setMoveVector(int offsetX, int offsetY) {
        ghostDX = offsetX;
        ghostDY = offsetY;
    }

    protected void updateCoordinates() {
        actualX += ghostDX * GHOST_SPEED;
        actualY += ghostDY * GHOST_SPEED;
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

    public Image getImage() {
        return actual;
    }
}
