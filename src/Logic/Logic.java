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
    //Objekter af klasser oprettes
    private screen screen;
    private User currentUser;
    private Api api;
    private ServerConnection serverConnection;

    //Arraylist af user, game og scores oprettes
    private ArrayList<User> users;
    private ArrayList<Game> games;
    private ArrayList<Game> deleteGames;
    private ArrayList<Score> scores;

    //Konstruktør oprettes
    public Logic() {
        screen = new screen();
        currentUser = new User();
        api = new Api();
        screen.setVisible(true);
    }

    //Metode som starte spillet, hvor alle actionslisterenes tilføjes de enkelte JPanels
    public void run() {
        screen.getLogin().addActionListener(new LoginActionListener());
        screen.getUsermenu().addActionListener(new UserMenuActionListener());
        screen.getDeleteGame().addActionListener(new DeleteGameActionListener());
        screen.getStartgame().addActionListener(new StartGameActionListener());
        screen.getHighscore().addActionListener(new HighscoreActionHandler());
        screen.getCreateGame().addActionListener(new CreateGameActionHandler());
    }

    //ActionLister til JPanel login
    private class LoginActionListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            //Input fra Jpanel bliver sat i currentUser objektet
            currentUser.setUsername(screen.getLogin().getUserNameInput().getText());
            currentUser.setPassword(screen.getLogin().getPasswordInput().getText());

            //API.login kaldes med currentUser som argument
            String serverResponse = api.login(currentUser);

            //Hvis knappen continue trykkes
            if (e.getSource() == screen.getLogin().getBtnContinue()) {

                //Kontrol-struktur som sikrer at brugeren findes
                if (serverResponse.equals("Login successful")) {
                    users = api.getUsers();

                    for (User logged : users) {
                        if (logged.getUsername().equals(screen.getLogin().getUserNameInput().getText())) {
                            currentUser = logged;
                        }
                        screen.show(GUI.screen.USERMENU);
                    }
                }

                //Pop-up vinduet som kommer hvis ovenstående kontrol-struktur ikke opfyldes
                else JOptionPane.showMessageDialog(screen, serverResponse);
            }
            if (e.getSource() == screen.getLogin().getBtnShutDown()) {
                System.exit(0);
            }
        }
    }

    //Actionslister til menuen, hvor de enkelte JPanel blicer kaldt alt efter hvilken knpe der trykkes på.
    //Hvis et JPanel skal vise noget data, vil der blive sat noget data i enkelte felter/comboboxes inden JPanel vises
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
                //screen.getHighscore().setGamesInCombobox(scores);
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

    //ActionListener til DeleteGame
    private class DeleteGameActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            //Hvis der trykkes på menu knappen skal menu skærmen vises
            if (e.getSource() == screen.getDeleteGame().getBtnMenu()) {

                screen.show(GUI.screen.USERMENU);
            }

            //Hvis der bliver trykket på knappen "Delete Game".
            if (e.getSource() == screen.getDeleteGame().getBtnDeleteGame()) {
                Game deleteGame = new Game();
                deleteGame.setName(screen.getDeleteGame().getSelectedDeleteGame());

                for (Game game : games) {
                    if (game.getName() == screen.getDeleteGame().getSelectedDeleteGame()) {
                        deleteGame = game;
                    }
                }

                //Svaret fra serveren modtages og der kontrolleres om spillet er slettet.
                String serverResponse = api.deleteGame(deleteGame.getGameId());
                if (serverResponse.equals("Game was deleted")) {
                    String name = deleteGame.getName();
                    JOptionPane.showMessageDialog(screen, name + "\nwas deleted");
                    screen.getDeleteGame().comboBoxDeletedGame.removeItemAt
                            (screen.getDeleteGame().comboBoxDeletedGame.getSelectedIndex());
                    screen.show(GUI.screen.USERMENU);
                }
            }
        }
    }

    //ActionListener til StartGame
    private class StartGameActionListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            //Hvis der trykkes på menu knappen skal menu skærmen vises
            if (e.getSource() == screen.getStartgame().getBtnMenu()) {
                screen.show(GUI.screen.USERMENU);
            }

            //Hvis knappen "Start Game" trykkes
            if (e.getSource() == screen.getStartgame().getBtnStartgame()) {

                //Game klassen oprettes som et objekt startGame
                Game startGame = new Game();

                for (Game openGame : games) {
                    if (openGame.getName().equals(screen.getStartgame().getComboBox().getSelectedItem())) {
                        startGame = openGame;
                    }
                }

                //Værdier til spillet opdateres
                Gamer opponent = new Gamer();
                opponent.setId(currentUser.getId());
                opponent.setControls(screen.getStartgame().getControlsTextfield().getText());
                startGame.setOpponent(opponent);

                //startGame metoden fra API-klassen kaldes med startGame som argument.
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

                //Alt afhængigt om brugeren har vundet eller ej vises en af de nedenstående Pop-ups
                if (currentUser.getUsername() != winnerName) {
                    JOptionPane.showMessageDialog(screen, "You lost to: \n" + winnerName);
                }
                if (currentUser.getUsername() == winnerName) {
                    JOptionPane.showMessageDialog(screen, "You won! Congratulations!");
                }
            }
        }
    }

    //ActionLister til highscores
    private class HighscoreActionHandler implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == screen.getHighscore().getBtnMenu()) {
                screen.show(GUI.screen.USERMENU);
            }
        }
    }

    //ActionsListener til CreatGame
    private class CreateGameActionHandler implements ActionListener {

        public void actionPerformed(ActionEvent e)

        {
            //Hvis der trykkes på menu knappen skal menu skærmen vises
            if (e.getSource() == screen.getCreateGame().getBtnMenu()) {
                screen.show(GUI.screen.USERMENU);
            }
            //Hvis knappen "Create Game" trykkes
            if (e.getSource() == screen.getCreateGame().getBtnCreateGame()) {

                //Objekter af hhv. Game og Gamer klasserne sættes til createGame og host
                Game createGame = new Game();
                Gamer host = new Gamer();

                //Værdier sættes enten fra JPanel eller med bestemt værdier.
                createGame.setMapSize(35);
                host.setId(currentUser.getId());
                createGame.setName(screen.getCreateGame().getTextFieldGameName().getText());
                host.setControls(screen.getCreateGame().getTextFieldHostControls().getText());
                createGame.setHost(host);
                createGame.setOpponent(null);
                //Metoden CreateGame fra api-klassen kaldes med createGame som argument.
                api.createGame(createGame);

                //Når spillet er oprettet kommer et pop-up vindue frem med spillets navn og brkræftelse på oprettelse
                JOptionPane.showMessageDialog(screen, "The game: \n" + createGame.getName() + "\nhas been created"
                        , "Confirmation", JOptionPane.INFORMATION_MESSAGE);

                screen.show(GUI.screen.USERMENU);
                screen.getCreateGame().getTextFieldGameName().setText("");
                screen.getCreateGame().getTextFieldHostControls().setText("");
            }
        }
    }
}