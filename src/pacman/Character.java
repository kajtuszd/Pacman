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
    protected final int CHARACTER_SPEED = 5;
    public int arrayPlace;


    abstract void loadImages();
    abstract void countSpawnCoordinates(short[] mazeData, int FIELD_SIZE, int WIDTH);

    protected void setMoveVector(int offsetX, int offsetY) {
        moveDX = offsetX;
        moveDY = offsetY;
    }

    protected void updateCoordinates() {
        actualX += moveDX * CHARACTER_SPEED;
        actualY += moveDY * CHARACTER_SPEED;
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

    protected void goThroughTunnelAndChangeSide() {
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
}
