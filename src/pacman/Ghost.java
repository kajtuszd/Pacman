package pacman;

abstract class Ghost extends Character {

    public Boolean isInHouse = true;
    private Boolean isLeftSide = true;

    public void goUp(short[] mazeData) {
        if (isHorizontalMoveValid() && canMoveUp(mazeData)) {
            setMoveVector(0, -1);
            turnUp();
        }
        updateCoordinates();
    }

    public void goDown(short[] mazeData) {
        if (isHorizontalMoveValid() && canMoveDown(mazeData)) {
            setMoveVector(0, 1);
            turnDown();
        }
        updateCoordinates();
    }

    public void goLeft(short[] mazeData) {
        if (isVerticalMoveValid() && canMoveLeft(mazeData)) {
            setMoveVector(-1, 0);
            turnLeft();
        }
        goThroughTunnelAndChangeSide();
        updateCoordinates();
    }

    public void goRight(short[] mazeData) {
        if (isVerticalMoveValid() && canMoveRight(mazeData)) {
            setMoveVector(1, 0);
            turnRight();
        }
        goThroughTunnelAndChangeSide();
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

    public void goLeftVerified(short[] mazeData) {
        if (mazeData[countNextLocation(-1, 0)] != 0) {
            goLeft(mazeData);
        }
    }

    public void goRightVerified(short[] mazeData) {
        if (mazeData[countNextLocation(1, 0) + 1] != 0) {
            goRight(mazeData);
            updateCoordinates();
        }
    }

    public void goUpVerified(short[] mazeData) {
        if (mazeData[countNextLocation(0, -1)] != 0) {
            goUp(mazeData);
        }
    }

    public void goDownVerified(short[] mazeData) {
        if (mazeData[countNextLocation(0, 1) + 28] != 0) {
            goDown(mazeData);
            updateCoordinates();
        }
    }
}
