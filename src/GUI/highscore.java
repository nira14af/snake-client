package GUI;

import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;

public class highscore extends JPanel {
    private JButton btnMenu;

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

        JLabel piclabel = new JLabel("");
        piclabel.setBounds(322, 13, 100, 91);
        add(piclabel);
    }

    public void addActionListener(ActionListener l) {
        btnMenu.addActionListener(l);
    }

    public JButton getBtnMenu() {
        return btnMenu;
    }
}