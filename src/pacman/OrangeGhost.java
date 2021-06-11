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

        @Override
        public void run() {
            super.run();
            if (!isInHouse) {
                evaluateNextMove(mazeData);
            }
        }

    }
}
