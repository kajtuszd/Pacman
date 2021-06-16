package pacman;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Leaderboard extends JFrame  {

    JLabel label;
    JButton button;
    JTextField textArea;
    JPanel panel;

    public Leaderboard() {
        label = new JLabel("nothing entered");
        button = new JButton("submit");
        LeaderboardConnector connector = new LeaderboardConnector();
        button.addActionListener(connector);
        panel = new JPanel();
        textArea = new JTextField(10);
        panel.add(textArea);
        panel.add(button);
        panel.add(label);
        add(panel);
        setSize(250, 300);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private class LeaderboardConnector implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String s = e.getActionCommand();
            if (s.equals("submit")) {
                label.setText(textArea.getText());
            }
        }
    }
}
