package GUI;

import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;

public class CreateGame extends JPanel {
    private JButton btnMenu;
    private JButton btnCreateGame;

    public CreateGame() {

        setBackground(Color.LIGHT_GRAY);
        setLayout(null);

        JLabel lbStartGame = new JLabel("Start Game");
        lbStartGame.setFont(new Font("Tahoma", Font.BOLD, 14));
        lbStartGame.setBounds(167, 35, 96, 23);
        add(lbStartGame);

        btnMenu = new JButton("Menu");
        btnMenu.setToolTipText("");
        btnMenu.setBounds(293, 255, 96, 23);
        add(btnMenu);

        btnCreateGame = new JButton("Create game");
        btnCreateGame.setBounds(35, 84, 128, 23);
        add(btnCreateGame);

        JLabel piclabel = new JLabel("");
        piclabel.setBounds(322, 13, 100, 91);
        add(piclabel);
    }

    public void addActionListener(ActionListener l) {
        btnMenu.addActionListener(l);
        btnCreateGame.addActionListener(l);
    }

    public JButton getBtnMenu() {
        return btnMenu;
    }

    public JButton getBtnCreateGame() {
        return btnCreateGame;
    }
}
