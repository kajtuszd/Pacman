package pacman;

import javax.swing.*;

public class Game extends JFrame {

    public Game() {
        add(new Maze());
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.setVisible(true);
        game.setTitle("Pacman");
        game.setSize(840, 1000);
    }
}
