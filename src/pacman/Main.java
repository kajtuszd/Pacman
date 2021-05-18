package pacman;

import javax.swing.*;

public class Main extends JFrame {

    public Main() {
        add(new Maze());
    }

    public static void main(String[] args) {
        Main game = new Main();
        game.setVisible(true);
        game.setTitle("Pacman");
        game.setSize(840, 930);
    }
}
