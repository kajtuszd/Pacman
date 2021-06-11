package pacman;

import javax.swing.*;

public class OrangeGhost extends Ghost {

    private final int SPAWN_PLACE = 8;

    public OrangeGhost(short[] mazeData, int FIELD_SIZE, int WIDTH) {
        loadImages();
        countSpawnCoordinates(mazeData, FIELD_SIZE, WIDTH);
        setMoveVector(0, 0);
        updateCoordinates();
    }

    @Override
    protected void loadImages() {
        up = new ImageIcon("media/ou.gif").getImage();
        down = new ImageIcon("media/od.gif").getImage();
        left = new ImageIcon("media/ol.gif").getImage();
        right = new ImageIcon("media/or.gif").getImage();
        actual = right;
    }

    @Override
    public void countSpawnCoordinates(short[] mazeData, int FIELD_SIZE, int WIDTH) {
        int spawnIndex = 0;
        for (int i = 0; i < mazeData.length; i++) {
            if (mazeData[i] == SPAWN_PLACE)
                spawnIndex = i;
        }
        int cols = spawnIndex / WIDTH;
        int rows = spawnIndex - cols * WIDTH;
        actualX = rows * FIELD_SIZE;
        actualY = cols * FIELD_SIZE;
    }

    public class AI extends Thread {

        short[] mazeData;
        int pacmanX;
        int pacmanY;

        public AI(short[] data, int pacX, int pacY) {
            mazeData = data;
            pacmanX = pacX;
            pacmanY = pacY;
        }

        private void evaluateNextMove() {
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

        @Override
        public void run() {
            super.run();
//            System.out.println("Hello from orange");
            if (!isInHouse) {
                evaluateNextMove();
            }
        }

    }
}
