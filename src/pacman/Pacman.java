package pacman;

import javax.swing.*;
import java.awt.*;

public class Pacman {

    private Image up, down, left, right, actual;
    private final int PACMAN_WIDTH = 30;
    private final int PACMAN_HEIGHT = 30;

    public Pacman() {
        up = new ImageIcon("media/pacman-up-resize.gif").getImage();
        down = new ImageIcon("media/pacman-down-resize.gif").getImage();
        left = new ImageIcon("media/pacman-left-resize.gif").getImage();
        right = new ImageIcon("media/pacman-right-resize.gif").getImage();
        actual = right;
    }

    public void turnUp() {
        actual = this.up;
    }

    public void turnDown() {
        actual = this.down;
    }

    public void turnRight() {
        actual = this.right;
    }

    public void turnLeft() {
        actual = this.left;
    }

    public Image getImage() {
        return actual;
    }
}
