package GUI;

import SDK.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class startGame extends JPanel {
    private JLabel lblStartGame;
    private JButton btnMenu;
    private JLabel lblSetControls;
    private JTextField ControlsTextfield;
    private JLabel lblChooseGame;
    private JComboBox comboBox;
    private JButton btnStartGame;

    public startGame() {

        setBackground(Color.LIGHT_GRAY);
        setLayout(null);

        lblStartGame = new JLabel("Start game");
        lblStartGame.setFont(new Font("Lucida Grande", Font.BOLD, 13));
        lblStartGame.setBounds(187, 6, 101, 16);
        add(lblStartGame);

        btnMenu = new JButton("Cancel");
        btnMenu.setBounds(301, 200, 117, 29);
        add(btnMenu);

        lblChooseGame = new JLabel("1. Choose game");
        lblChooseGame.setBounds(40, 70, 175, 16);
        add(lblChooseGame);

        comboBox = new JComboBox();
        comboBox.setBounds(40, 85, 134, 27);
        add(comboBox);

        lblSetControls = new JLabel("2. Set controls (W,A,S,D)");
        lblSetControls.setBounds(208, 70, 175, 16);
        add(lblSetControls);

        ControlsTextfield = new JTextField();
        ControlsTextfield.setBounds(200, 83, 134, 28);
        add(ControlsTextfield);

        btnStartGame = new JButton("Start game");
        btnStartGame.setBounds(40, 200, 117, 29);
        add(btnStartGame);}

    public void addActionListener(ActionListener l) {
        btnMenu.addActionListener(l);
        btnStartGame.addActionListener(l);
    }

    public void setGames (ArrayList<Game> games){

        for (Game openGames : games) {
            comboBox.addItem(openGames.getName());
        }
    }

    public JComboBox getComboBox (){
        return comboBox;
    }

    public JButton getBtnMenu() {
        return btnMenu;
    }

    public JButton getBtnShutDown() {
        return btnStartGame;
    }
}
