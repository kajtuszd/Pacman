package pacman;

import javax.swing.*;
import java.awt.*;

public class Pacman {

    private Image up, down, left, right;
    private final int PACMAN_WIDTH = 30;
    private final int PACMAN_HEIGHT = 30;

    public Pacman() {
        up = new ImageIcon("media/pacman-up-resize.gif").getImage();
        down = new ImageIcon("media/pacman-down-resize.gif").getImage();
        left = new ImageIcon("media/pacman-left-resize.gif").getImage();
        right = new ImageIcon("media/pacman-right-resize.gif").getImage();
    }
    public Image turnUp() {
        return this.up;
    }
    public Image turnDown() {
        return this.down;
    }
    public Image turnRight() {
        return this.right;
    }
    public Image turnLeft() {
        return this.left;
    }
}
