package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class highscore extends JPanel {
    private JButton btnMenu;
    private JButton btnHighscore;

    public highscore() {

        setBackground(Color.LIGHT_GRAY);
        setLayout(null);

        JLabel lblHighScores = new JLabel("Snake high scores");
        lblHighScores.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblHighScores.setBounds(149, 35, 161, 23);
        add(lblHighScores);

        btnMenu = new JButton("Menu");
        btnMenu.setBounds(312, 254, 96, 23);
        add(btnMenu);

        btnHighscore = new JButton("Show highscore");
        btnHighscore.setBounds(15, 254, 150, 23);
        add(btnHighscore);

        JLabel piclabel = new JLabel("");
        piclabel.setBounds(322, 13, 100, 91);
        add(piclabel);

    }

    public void addActionListener(ActionListener l) {
        btnMenu.addActionListener(l);
        btnHighscore.addActionListener(l);
    }

    public JButton getBtnMenu() {
        return btnMenu;
    }

    public JButton getBtnHighscore(){
        return btnHighscore;
    }
}