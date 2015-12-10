package GUI;

import SDK.Api;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class highscore extends JPanel {

    public JTextField usersTotal;
    //Variabler til GUI deklareres
    private JScrollPane scrollPane;
    private JButton btnMenu;
    private JTable highScoreTable;

    //JPanel for HighScore med ovenstående variabler
    public highscore() {
        setBackground(Color.LIGHT_GRAY);
        setLayout(null);

        scrollPane = new JScrollPane();
        scrollPane.setBounds(0, 90, 427, 171);
        add(scrollPane);

        highScoreTable = new JTable();
        scrollPane.setColumnHeaderView(highScoreTable);
        scrollPane.setViewportView(highScoreTable);

        JLabel lblAllUsers = new JLabel("Highscores");
        lblAllUsers.setBounds(192, 30, 89, 19);
        add(lblAllUsers);
        lblAllUsers.setFont(new Font("Tahoma", Font.BOLD, 15));

        btnMenu = new JButton("Menu");
        btnMenu.setBounds(0, 262, 89, 25);
        add(btnMenu);
        btnMenu.setAlignmentY(Component.BOTTOM_ALIGNMENT);

    }

    //ActionListers til knapperne tilføjes
    public void addActionListener(ActionListener e) {
        btnMenu.addActionListener(e);
    }

    public JButton getBtnMenu() {
        return btnMenu;
    }

    //Metode som via en ArrayList sætter highscores i JComboBox
//	public void setTableshowHighscore(ArrayList<Score> highscores) {
//		HighScoreTableModel highscoreTableModel = new HighScoreTableModel(highscores);
//		highScoreTable.setModel(highscoreTableModel);
//	}

    //TableModel til at vise data oprettes til at vise highscores på tværs af alle spil.
    class HighScoreTableModel extends AbstractTableModel {

        private Api api;

        //Ovjekt af API oprettes
        public HighScoreTableModel() {
            api = new Api();
        }

        //Antallet af kolonner defineres
        public int getColumnCount() {
            return 3;
        }

        //Metode fra api.kaldes, hvor der hentes highscore  fra serveren
        public int getRowCount() {
            return api.getHighscores().size();
        }

        //Kolonnenavnene defineres
        public String getColumnName(int columnIndex) {
            switch (columnIndex) {
                case 0:
                    return "username";
                case 1:
                    return "game_name";
                case 2:
                    return "score";
            }
            return null;
        }

        //Værdierne til de ovenstående kolonner hentes fra highscore() i API'et.
        public Object getValueAt(int rowIndex, int columnIndex) {
            api.getHighscores().get(rowIndex);
            switch (columnIndex) {

                case 0:
                    return api.getHighscores().get(rowIndex).getUser();
                case 1:
                    return api.getHighscores().get(rowIndex).getGame();
                case 2:
                    return api.getHighscores().get(rowIndex).getScore();
            }
            return getValueAt(rowIndex, columnIndex);
        }
    }
}