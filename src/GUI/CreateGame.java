package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CreateGame extends JPanel {
    private JButton btnMenu;
    private JButton btnCreateGame;
    private JTextField textFieldGameName;
    private JTextField textFieldHostControls;


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
        btnCreateGame.setBounds(33, 255, 128, 23);
        add(btnCreateGame);

        JLabel piclabel = new JLabel("");
        piclabel.setBounds(322, 13, 100, 91);
        add(piclabel);

        JLabel lblGameName = new JLabel("Game Name");
        lblGameName.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblGameName.setBounds(33, 81, 96, 23);
        add(lblGameName);

        textFieldGameName = new JTextField();
        textFieldGameName.setBounds(152, 76, 134, 28);
        add(textFieldGameName);
        textFieldGameName.setColumns(10);

        JLabel lblControls = new JLabel("Controls");
        lblControls.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblControls.setBounds(33, 165, 96, 23);
        add(lblControls);

        textFieldHostControls = new JTextField();
        textFieldHostControls.setColumns(10);
        textFieldHostControls.setBounds(152, 163, 134, 28);
        add(textFieldHostControls);

        JLabel lblChooseYourControls = new JLabel("Choose your controls (A, S, D W)");
        lblChooseYourControls.setFont(new Font("Tahoma", Font.PLAIN, 9));
        lblChooseYourControls.setBounds(293, 167, 167, 23);
        add(lblChooseYourControls);
    }

    public void addActionListener(ActionListener l) {
        btnMenu.addActionListener(l);
        btnCreateGame.addActionListener(l);
    }

    public JTextField getTextFieldGameName() {
        return textFieldGameName;
    }

    public JTextField getTextFieldHostControls() {
        return textFieldHostControls;
    }

    public void setTextFieldHostControls(JTextField textFieldHostControls) {
        this.textFieldHostControls = textFieldHostControls;
    }

    public JButton getBtnMenu() {
        return btnMenu;
    }

    public JButton getBtnCreateGame() {
        return btnCreateGame;
    }


}
