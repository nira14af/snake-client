package GUI;

import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.Color;

public class startGame extends JPanel {
    private JLabel lblFindGame;
    private JButton btnMenu;
    private JLabel lblSetGameDirections;
    private JTextField directionsTextfield;
    private JLabel lblChooseOpponent;
    private JComboBox comboBox;
    private JButton btnStartGame;

    public startGame() {
        setLayout(null);

        lblFindGame = new JLabel("Find game");
        lblFindGame.setFont(new Font("Lucida Grande", Font.BOLD, 13));
        lblFindGame.setBounds(187, 6, 101, 16);
        add(lblFindGame);

        btnMenu = new JButton("Cancel");
        btnMenu.setBounds(310, 240, 117, 29);
        add(btnMenu);

        lblChooseOpponent = new JLabel("1. Choose opponent");
        lblChooseOpponent.setBounds(40, 70, 175, 16);
        add(lblChooseOpponent);

        comboBox = new JComboBox();
        comboBox.setBounds(40, 85, 52, 27);
        add(comboBox);

        lblSetGameDirections = new JLabel("2. Set directions (W,A,S,D)");
        lblSetGameDirections.setBounds(40, 130, 175, 16);
        add(lblSetGameDirections);

        directionsTextfield = new JTextField();
        directionsTextfield.setBounds(40, 150, 134, 28);
        add(directionsTextfield);

        btnStartGame = new JButton("Start game");
        btnStartGame.setBounds(40, 200, 117, 29);
        add(btnStartGame);}

    public void addActionListener(ActionListener l) {
        btnMenu.addActionListener(l);
        btnStartGame.addActionListener(l);
    }

    public JButton getBtnMenu() {
        return btnMenu;
    }

    public JButton getBtnShutDown() {
        return btnStartGame;
    }
}
