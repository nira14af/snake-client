package GUI;

import SDK.Model.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class deleteGame extends JPanel {

    public JComboBox comboBoxDeletedGame;
    //Variabler til GUI deklareres
    private JButton btnMenu;
    private JButton btnDeleteGame;

    //JPanel for DeleteGame med ovenstående variabler
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

        comboBoxDeletedGame = new JComboBox();
        comboBoxDeletedGame.setBounds(40, 105, 150, 27);
        add(comboBoxDeletedGame);
    }

    public void addActionListener(ActionListener l) {
        btnMenu.addActionListener(l);
        btnDeleteGame.addActionListener(l);
    }

    public void setGamesInCombobox(ArrayList<Game> games) {
        comboBoxDeletedGame.removeAllItems();
        for (Game g : games) {
            comboBoxDeletedGame.addItem(g.getName());
        }
    }

    public JButton getBtnMenu() {
        return btnMenu;
    }

    public JButton getBtnDeleteGame() {
        return btnDeleteGame;
    }

    public void deleteSelectedGame() {
        comboBoxDeletedGame.removeItemAt(comboBoxDeletedGame.getSelectedIndex());
    }

    public String getSelectedDeleteGame() {
        return (String) comboBoxDeletedGame.getSelectedItem();
    }

    public void removeGame() {
        comboBoxDeletedGame.removeItemAt(comboBoxDeletedGame.getSelectedIndex());
    }
}
