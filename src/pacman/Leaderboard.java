package pacman;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Leaderboard extends JFrame  {

    JLabel label;
    JButton button;
    JTextField textArea;
    JPanel panel;
    private static final String leaderboardFile = "./utils/leaderboard.txt";
    private String[] names = new String[5];
    private int[] scores = new int[5];
    private static int newHighscoreIndex;


    public Leaderboard(int result) {
        setHighScore(result);
    }

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

    private String getHighscoreText() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            builder.append(names[i]).append(" ").append(scores[i]).append("   ");
        }
        return builder.toString();
    }

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

    private Boolean isHighScore(int result) {
        return result > scores[4];
    }

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

    private void getNewHighscoreIndex(int result) {
        for(int i = 0; i < scores.length; i++) {
            if(scores[i] == result) {
                newHighscoreIndex = i;
            }
        }
    }

    private class LeaderboardConnector implements ActionListener {

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
