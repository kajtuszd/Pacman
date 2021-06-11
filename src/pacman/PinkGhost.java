package pacman;

import javax.swing.*;

/**
 * Pink ghost class
 */
public class PinkGhost extends Ghost {

    private final int SPAWN_PLACE = 9;

    /**
     * Pink Ghost constructor
     * @param mazeData array storing maze data
     * @param FIELD_SIZE field size in pixels
     * @param WIDTH maze width
     */
    public PinkGhost(short[] mazeData, int FIELD_SIZE, int WIDTH) {
        loadImages();
        countSpawnCoordinates(mazeData, FIELD_SIZE, WIDTH);
        setMoveVector(0, 0);
        updateCoordinates();
    }

    /**
     * Method used to load ghost images from files
     */
    @Override
    protected void loadImages() {
        up = new ImageIcon("media/pu.gif").getImage();
        down = new ImageIcon("media/pd.gif").getImage();
        left = new ImageIcon("media/pl.gif").getImage();
        right = new ImageIcon("media/pr.gif").getImage();
        actual = right;
    }

    /**
     * Method used to find pink ghost spawn place
     * @param mazeData array storing maze data
     * @param FIELD_SIZE field size in pixels
     * @param WIDTH maze width
     */
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

    /**
     * Pink Ghost AI
     */
    public class AI extends Thread {

        short[] mazeData;

        /**
         * @param data array storing maze data
         */
        public AI(short[] data) {
            mazeData = data;
        }

        /**
         * Method used to create thread and choose next ghost move
         */
        @Override
        public void run() {
            super.run();
            if (!isInHouse) {
                evaluateNextMove(mazeData);
            }
        }
    }
}
