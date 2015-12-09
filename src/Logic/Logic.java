package Logic;

import GUI.screen;
import SDK.Api;
import SDK.Model.Game;
import SDK.Model.Gamer;
import SDK.Model.Score;
import SDK.Model.User;
import SDK.ServerConnection;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Logic {
    private screen screen;
    private User currentUser;
    private Api api;
    private ArrayList<User> users;
    private ArrayList<Game> games;
    private ArrayList<Game> deleteGames;
    private ArrayList<Score> scores;
    private ServerConnection serverConnection;

    public Logic() {
        screen = new screen();
        currentUser = new User();
        api = new Api();
        screen.setVisible(true);
    }

    public void run() {
        screen.getLogin().addActionListener(new LoginActionListener());
        screen.getUsermenu().addActionListener(new UserMenuActionListener());
        screen.getDeleteGame().addActionListener(new DeleteGameActionListener());
        screen.getStartgame().addActionListener(new StartGameActionListener());
        screen.getHighscore().addActionListener(new HighscoreActionHandler());
        screen.getCreateGame().addActionListener(new CreateGameActionHandler());
    }

    private class LoginActionListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            currentUser.setUsername(screen.getLogin().getUserNameInput().getText());
            currentUser.setPassword(screen.getLogin().getPasswordInput().getText());

            String serverResponse = api.login(currentUser);

            if (e.getSource() == screen.getLogin().getBtnContinue()) {

                if (serverResponse.equals("Login successful")) {
                    users = api.getUsers();

                    for (User logged : users) {
                        if (logged.getUsername().equals(screen.getLogin().getUserNameInput().getText())) {
                            currentUser = logged;
                        }
                        screen.show(GUI.screen.USERMENU);
                    }
                } else JOptionPane.showMessageDialog(screen, serverResponse);
            }
            if (e.getSource() == screen.getLogin().getBtnShutDown()) {
                System.exit(0);
            }
        }
    }

    private class UserMenuActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e)
        {
            if (e.getSource() == screen.getUsermenu().getBtnLogOut()) {
                screen.show(GUI.screen.LOGIN);
                screen.getLogin().userNameInput.setText("");
                screen.getLogin().passwordInput.setText("");
            } else if (e.getSource() == screen.getUsermenu().getStartGame()) {
                screen.show(GUI.screen.STARTGAME);
                games = api.getGames();
                screen.getStartgame().setGamesInCombobox(games);
            } else if (e.getSource() == screen.getUsermenu().getHighscore()) {
                scores = api.getHighscores();
                //screen.getHighscore().setTableshowHighscore(scores);
                screen.show(screen.HIGHSCORE);

            } else if (e.getSource() == screen.getUsermenu().getDeleteGame()) {
                screen.show(GUI.screen.DELETEGAME);
                games = api.getGames();
                screen.getDeleteGame().setGamesInCombobox(games);
            } else if (e.getSource() == screen.getUsermenu().getBtnCreateGame()) {
                screen.show(GUI.screen.CREATEGAME);
            }
        }
    }

    private class DeleteGameActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == screen.getDeleteGame().getBtnMenu()) {

                screen.show(GUI.screen.USERMENU);
            }

            if (e.getSource() == screen.getDeleteGame().getBtnDeleteGame()) {
                Game deleteGame = new Game();
                deleteGame.setName(screen.getDeleteGame().getSelectedDeleteGame());

                for (Game game : games) {
                    if (game.getName() == screen.getDeleteGame().getSelectedDeleteGame()) {
                        deleteGame = game;
                    }
                }

                String message = api.deleteGame(deleteGame.getGameId());
                if (message.equals("Game was deleted")) {
                    String name = deleteGame.getName();
                    JOptionPane.showMessageDialog(screen, name + "\nwas deleted");
                    screen.getDeleteGame().comboBoxDeletedGame.removeItemAt
                            (screen.getDeleteGame().comboBoxDeletedGame.getSelectedIndex());
                    screen.show(GUI.screen.USERMENU);
                }

            }
        }
    }

    private class StartGameActionListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == screen.getStartgame().getBtnMenu()) {
                screen.show(GUI.screen.USERMENU);
            }

            if (e.getSource() == screen.getStartgame().getBtnStartgame()) {

                Game startGame = new Game();

                for (Game openGame : games) {
                    if (openGame.getName().equals(screen.getStartgame().getComboBox().getSelectedItem())) {
                        startGame = openGame;
                    }
                }

                Gamer opponent = new Gamer();
                opponent.setId(currentUser.getId());
                opponent.setControls(screen.getStartgame().getControlsTextfield().getText());
                startGame.setOpponent(opponent);
                String returnMessage = api.startGame(startGame);
                String winnerName = "";

                for (User winner : users) {
                    try {
                        if (winner.getId() == Integer.parseInt(returnMessage)) {
                            winnerName = winner.getUsername();
                        }
                    } catch (NumberFormatException e1) {
                        e1.printStackTrace();
                    }
                }

                if (currentUser.getUsername() != winnerName) {
                    JOptionPane.showMessageDialog(screen, "You lost to: \n" + winnerName);
                }
                if (currentUser.getUsername() == winnerName) {
                    JOptionPane.showMessageDialog(screen, "You won! Congratulations!");
                }
            }
        }
    }

    private class HighscoreActionHandler implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == screen.getHighscore().getBtnMenu()) {
                screen.show(GUI.screen.USERMENU);
            }
        }
    }

    private class CreateGameActionHandler implements ActionListener {

        public void actionPerformed(ActionEvent e)

        {
            if (e.getSource() == screen.getCreateGame().getBtnMenu()) {
                screen.show(GUI.screen.USERMENU);
            }

            if (e.getSource() == screen.getCreateGame().getBtnCreateGame()) {
                Game createGame = new Game();
                Gamer host = new Gamer();

                createGame.setMapSize(35);
                host.setId(currentUser.getId());
                createGame.setName(screen.getCreateGame().getTextFieldGameName().getText());
                host.setControls(screen.getCreateGame().getTextFieldHostControls().getText());
                createGame.setHost(host);
                createGame.setOpponent(null);
                api.createGame(createGame);

                JOptionPane.showMessageDialog(screen, "The game: \n" + createGame.getName() + "\nhas been created"
                        , "Confirmation", JOptionPane.INFORMATION_MESSAGE);

                screen.show(GUI.screen.USERMENU);
                screen.getCreateGame().getTextFieldGameName().setText("");
                screen.getCreateGame().getTextFieldHostControls().setText("");
            }
        }
    }
}