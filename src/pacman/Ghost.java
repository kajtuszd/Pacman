package pacman;

/**
 * Abstract ghost class
 */
abstract class Ghost extends Character {

    public Boolean isInHouse = true;
    private Boolean isLeftSide = true;

    /**
     * Force ghost to go up
     * @param mazeData array storing maze data
     */
    public void goUp(short[] mazeData) {
        if (isHorizontalMoveValid() && canMoveUp(mazeData)) {
            setMoveVector(0, -1);
            turnUp();
        }
        updateCoordinates();
    }

    /**
     * Force ghost to go down
     * @param mazeData array storing maze data
     */
    public void goDown(short[] mazeData) {
        if (isHorizontalMoveValid() && canMoveDown(mazeData)) {
            setMoveVector(0, 1);
            turnDown();
        }
        updateCoordinates();
    }

    /**
     * Force ghost to go left
     * @param mazeData array storing maze data
     */
    public void goLeft(short[] mazeData) {
        if (isVerticalMoveValid() && canMoveLeft(mazeData)) {
            setMoveVector(-1, 0);
            turnLeft();
        }
        goThroughTunnelAndChangeSide();
        updateCoordinates();
    }

    /**
     * Force ghost to go right
     * @param mazeData array storing maze data
     */
    public void goRight(short[] mazeData) {
        if (isVerticalMoveValid() && canMoveRight(mazeData)) {
            setMoveVector(1, 0);
            turnRight();
        }
        goThroughTunnelAndChangeSide();
        updateCoordinates();
    }

    /**
     * Method checking if ghost went out of home
     * @param mazeData array storing maze data
     * @return is ghost out of home
     */
    private Boolean checkIfGhostCrossedWhiteLine(short[] mazeData) {
        return (mazeData[arrayPlace + 28] == 4 && actualY % 30 == 0);
    }

    /**
     * Make ghost go out of home
     * @param mazeData array storing maze data
     */
    public void goOutOfHouse(short[] mazeData) {
        if (checkIfGhostCrossedWhiteLine(mazeData)) {
            isInHouse = false;
            setMoveVector(0, 0);
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

    /**
     * Method used to change ghost movement direction
     * @param vecX horizontal vector
     * @param vecY vertical vector
     */
    protected void decideWhereToMove(int vecX, int vecY) {
        if (vecX == 1 && vecY == 0) {
            setMoveVector(1, 0);
            turnRight();
        }
        if (vecX == -1 && vecY == 0) {
            setMoveVector(-1, 0);
            turnLeft();
        }
        if (vecX == 0 && vecY == -1) {
            setMoveVector(0, -1);
            turnUp();
        }
        if (vecX == 0 && vecY == 1) {
            setMoveVector(0, 1);
            turnDown();
        }
    }

    /**
     * Method used to draw next ghost move
     * @param mazeData array storing maze data
     */
    protected void evaluateNextMove(short[] mazeData) {
        if (mazeData[countNextLocation(moveDX, moveDY)] == 0 || mazeData[countNextLocation(moveDX, moveDY)] == 0
                || mazeData[countNextLocation(moveDX, moveDY)] == 4) {
            setMoveVector(0,0);
        }
        if (isOnNode(mazeData) || (moveDX == 0 && moveDY == 0)) {
            int randomNumber = (int) ((Math.random() * (4)));
            int vectX = 0, vectY = 0;
            if (randomNumber == 0 && mazeData[countNextLocation(-1, 0)] != 0) { // left
                vectX = -1;
                vectY = 0;
            }
            if (randomNumber == 1 && mazeData[countNextLocation(1, 0) + 1] != 0) { // right
                vectX = 1;
                vectY = 0;
            }
            if (randomNumber == 2 && mazeData[countNextLocation(0, 1) + 28] != 0 &&
                    mazeData[countNextLocation(0, 1) + 28] != 4) { // down
                vectX = 0;
                vectY = 1;
            }
            if (randomNumber == 3 && mazeData[countNextLocation(0, -1 )] != 0) { // up
                vectX = 0;
                vectY = -1;
            }
            decideWhereToMove(vectX, vectY);
        }
        updateCoordinates();
        goThroughTunnelAndChangeSide();
    }
}
