package pacman;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Leaderboard class
 */
public class Leaderboard extends JFrame  {

    JLabel label;
    JButton button;
    JTextField textArea;
    JPanel panel;
    private static final String leaderboardFile = "./utils/leaderboard.txt";
    private String[] names = new String[5];
    private int[] scores = new int[5];
    private static int newHighscoreIndex;


    /**
     * Leaderboard constructor
     * @param result latest game result
     */
    public Leaderboard(int result) {
        setHighScore(result);
    }

    /**
     * Method displaying leaderboard window
     */
    private void initializeLeaderboard() {
        button = new JButton("submit");
        LeaderboardConnector connector = new LeaderboardConnector();
        button.addActionListener(connector);
        createLeaderboardFile();
        readFromLeaderboardFile();
        label = new JLabel();
        label.setText(getHighscoreText());
        panel = new JPanel();
        textArea = new JTextField(10);
        panel.add(textArea);
        panel.add(button);
        panel.add(label);
        add(panel);
        setSize(650, 200);
        setVisible(true);
    }

    /**
     * Method to create leaderboard text from processed data
     * @return text with leaderboard to be displayed
     */
    private String getHighscoreText() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            builder.append(names[i]).append(" ").append(scores[i]).append("   ");
        }
        return builder.toString();
    }

    /**
     * Method to create leaderboard file
     */
    private void createLeaderboardFile() {
        File file = new File(leaderboardFile);
        if (!file.exists()) {
            try {
                FileWriter writer = new FileWriter(leaderboardFile);
                for (int i = 0; i < 4; i++) {
                    writer.write("............... 0 \n");
                }
                writer.write("............... 0");
                writer.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Method to write leaderboard to a file
     */
    private void writeLeaderboardToFile() {
        try {
            FileWriter writer = new FileWriter(leaderboardFile);
            for (int i = 0; i < 4; i++) {
                writer.write(names[i] + " " + scores[i] + "\n");
            }
            writer.write(names[4] + " " + scores[4]);
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to read leaderboard from file
     */
    private void readFromLeaderboardFile() {
        try {
            File file = new File(leaderboardFile);
            Scanner scanner = new Scanner(file);
            for (int i = 0; scanner.hasNextLine(); i++) {
                names[i] = scanner.next();
                scores[i] = scanner.nextInt();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to check if latest game result should be saved to leaderboard
     * @param result latest game result
     * @return is result a highscore
     */
    private Boolean isHighScore(int result) {
        return result > scores[4];
    }

    /**
     * Method to set new highscore to leaderboard
     * @param result latest game result
     */
    public void setHighScore(int result) {
        if (isHighScore(result)) {
            initializeLeaderboard();
            scores[4] = result;
            int counter = 4;
            while (counter > 0) {
                if (result > scores[counter - 1]) {
                    int temp = scores[counter - 1];
                    scores[counter - 1] = scores[counter];
                    scores[counter] = temp;
                    String name = names[counter - 1];
                    names[counter - 1] = names[counter];
                    names[counter] = name;
                }
                counter--;
            }
            getNewHighscoreIndex(result);
        }
    }

    /**
     * Find new high score index
     * @param result new high score index
     */
    private void getNewHighscoreIndex(int result) {
        for(int i = 0; i < scores.length; i++) {
            if(scores[i] == result) {
                newHighscoreIndex = i;
            }
        }
    }

    /**
     * Action listener for dialog box
     */
    private class LeaderboardConnector implements ActionListener {

        /**
         * Method to submit new high score
         * @param e event used to recognize action
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            String s = e.getActionCommand();
            if (s.equals("submit")) {
                names[newHighscoreIndex] = textArea.getText();
                writeLeaderboardToFile();
                readFromLeaderboardFile();
                label.setText(getHighscoreText());
            }
        }
    }
}
