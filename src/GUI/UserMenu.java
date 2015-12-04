package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class UserMenu extends JPanel {

    private JLabel lblGameMenu;
    private JButton btnStartGame;
    private JButton btnHighscore;
    private JButton btnDeleteGame;
    private JButton btnLogOut;
    private JButton btnCreateGame;

    public UserMenu() {
        setBackground(Color.LIGHT_GRAY);
        setLayout(null);

        lblGameMenu = new JLabel("Game menu");
        lblGameMenu.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblGameMenu.setBounds(187, 33, 93, 17);
        add(lblGameMenu);

        btnStartGame = new JButton("Start game");
        btnStartGame.setBounds(164, 78, 150, 23);
        add(btnStartGame);

        btnHighscore = new JButton("Show highscore");
        btnHighscore.setBounds(164, 112, 150, 23);
        add(btnHighscore);

        btnDeleteGame = new JButton("Delete game");
        btnDeleteGame.setBounds(164, 146, 150, 23);
        add(btnDeleteGame);

        btnCreateGame = new JButton("Create game");
        btnCreateGame.setBounds(164, 170, 150, 23);
        add(btnCreateGame);

        btnLogOut = new JButton("Log out");
        btnLogOut.setBounds(267, 216, 108, 23);
        add(btnLogOut);

        JLabel piclabel = new JLabel("");
        piclabel.setBounds(322, 13, 100, 91);
        add(piclabel);

    }

    public void addActionListener(ActionListener l) {
        btnStartGame.addActionListener(l);
        btnHighscore.addActionListener(l);
        btnDeleteGame.addActionListener(l);
        btnCreateGame.addActionListener(l);
        btnLogOut.addActionListener(l);
    }

    public JLabel getLblUserMenu() {
        return lblGameMenu;
    }

    public JButton getStartGame() {
        return btnStartGame;
    }

    public JButton getBtnLogOut() {
        return btnLogOut;
    }

    public JButton getHighscore() {
        return btnHighscore;
    }

    public JButton getDeleteGame() {
        return btnDeleteGame;
    }

    public JButton getBtnCreateGame() {
        return btnCreateGame;
    }
}
