package pacman;

import javax.swing.*;
import java.awt.*;

public class PinkGhost extends Ghost {

    private final int SPAWN_PLACE = 9;

    public PinkGhost(short[] mazeData, int FIELD_SIZE, int WIDTH) {
        loadImages();
        countSpawnCoordinates(mazeData, FIELD_SIZE, WIDTH);
        setMoveVector(0, 0);
        updateCoordinates();
    }

    @Override
    protected void loadImages() {
        up = new ImageIcon("media/pu.gif").getImage();
        down = new ImageIcon("media/pd.gif").getImage();
        left = new ImageIcon("media/pl.gif").getImage();
        right = new ImageIcon("media/pr.gif").getImage();
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
