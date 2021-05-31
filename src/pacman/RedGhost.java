package pacman;

import javax.swing.*;

public class RedGhost extends Ghost implements GameCharacter{

    private final int SPAWN_PLACE = 6;

    public RedGhost(short[] mazeData, int FIELD_SIZE, int WIDTH) {
        loadImages();
        countSpawnCoordinates(mazeData, FIELD_SIZE, WIDTH);
    }

    @Override
    public void loadImages() {
        up = new ImageIcon("media/ru.gif").getImage();
        down = new ImageIcon("media/rd.gif").getImage();
        left = new ImageIcon("media/rl.gif").getImage();
        right = new ImageIcon("media/rr.gif").getImage();
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
}
