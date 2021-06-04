package pacman;

import javax.swing.*;
import java.awt.*;

public class BlueGhost extends Character {

    private final int SPAWN_PLACE = 7;

    public BlueGhost(short[] mazeData, int FIELD_SIZE, int WIDTH) {
        loadImages();
        countSpawnCoordinates(mazeData, FIELD_SIZE, WIDTH);
        setMoveVector(0, 0);
        updateCoordinates();
    }

    @Override
    protected void loadImages() {
        up = new ImageIcon("media/bu.gif").getImage();
        down = new ImageIcon("media/bd.gif").getImage();
        left = new ImageIcon("media/bl.gif").getImage();
        right = new ImageIcon("media/br.gif").getImage();
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


    public static class AI extends Thread {
        @Override
        public void run() {
            super.run();
//            System.out.println("Hello from blue");
        }
    }
}
