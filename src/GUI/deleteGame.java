package GUI;

import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;

public class deleteGame extends JPanel {
    private JButton btnMenu;
    private JButton btnDeleteGame;

    public deleteGame() {

        setBackground(Color.LIGHT_GRAY);
        setLayout(null);

        JLabel lbDeleteGame = new JLabel("Delete Game");
        lbDeleteGame.setFont(new Font("Tahoma", Font.BOLD, 14));
        lbDeleteGame.setBounds(167, 35, 96, 23);
        add(lbDeleteGame);

        btnMenu = new JButton("Menu");
        btnMenu.setToolTipText("");
        btnMenu.setBounds(284, 230, 96, 23);
        add(btnMenu);

        btnDeleteGame = new JButton("Delete game");
        btnDeleteGame.setBounds(58, 230, 96, 23);
        add(btnDeleteGame);

    }

    public void addActionListener(ActionListener l) {
        btnMenu.addActionListener(l);
        btnDeleteGame.addActionListener(l);

    }

    public JButton getBtnMenu() {
        return btnMenu;
    }

    public JButton getBtnDeleteGame() {
        return btnDeleteGame;
    }

}
