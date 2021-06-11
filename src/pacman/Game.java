package pacman;

import javax.swing.*;

/**
 * Main game class
 */
public class Game extends JFrame {

    /**
     * Add maze to game
     */
    public Game() {
        add(new Maze());
    }

    /**
     * Create game object
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.setVisible(true);
        game.setTitle("Pacman");
        game.setSize(840, 1000);
    }
}
