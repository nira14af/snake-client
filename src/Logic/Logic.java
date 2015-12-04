package Logic;

import GUI.*;
import SDK.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Logic {
    public startGame StartGame;
    public login login;
    public UserMenu userMenu;
    public highscore highscore;
    public Api api;
    private User currentUser;
    private GUI.screen screen;
    private ArrayList<User> users;
    private ArrayList<Game> games;
    private ArrayList<Game> deleteGames;
    private ArrayList<Score> showHighscores;


    public Logic() {
        screen = new screen();
        currentUser = new User();
        screen.setVisible(true);
    }

    public void run() {
        screen.getLogin().addActionListener(new LoginActionListener());
        screen.getUsermenu().addActionListener(new UsermenuActionListener());
        screen.getStartgame().addActionListener(new StartGameActionListener());
        screen.getHighscore().addActionListener(new ShowHighScoreActionListener());
        screen.getDeleteGame().addActionListener(new DeleteGameActionListener());
    }

    private class LoginActionListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            currentUser.setUsername(screen.getLogin().getUserNameInput().getText());
            currentUser.setPassword(screen.getLogin().getPasswordInput().getText());

            String message = api.login(currentUser);

            if (e.getSource() == screen.getLogin().getBtnContinue()) {

                if (message.equals("Login successful")) {
                    users = api.getUsers();
                    for (User logged : users) {
                        if (logged.getUsername().equals(screen.getLogin().getUserNameInput().getText())) {
                            currentUser = logged;
                            System.out.println(currentUser.getId());
                        }
                    }
                    screen.show(GUI.screen.USERMENU);
                } else JOptionPane.showMessageDialog(screen, message);
            }
            if (e.getSource() == screen.getLogin().getBtnShutDown()) {
                System.exit(0);
            }
        }
    }

    public class UsermenuActionListener implements ActionListener {

        public void actionPerformed(ActionEvent e)

        {
            if (e.getSource() == screen.getUsermenu().getBtnLogOut()) {
                screen.show(GUI.screen.LOGIN);
                screen.getLogin().userNameInput.setText("");
                screen.getLogin().passwordInput.setText("");
            } else if (e.getSource() == screen.getUsermenu().getStartGame()) {
                screen.show(GUI.screen.STARTGAME);
                //  games = api.getGames(currentUser.getId());
                screen.getStartgame().setGames(games);

            } else if (e.getSource() == screen.getUsermenu().getHighscore()) {
                screen.show(GUI.screen.HIGHSCORE);

            } else if (e.getSource() == screen.getUsermenu().getDeleteGame()) {
                screen.show(GUI.screen.DELETEGAME);
            }
        }
    }

    public class StartGameActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == screen.getStartgame().getBtnMenu()) {
                screen.show(GUI.screen.USERMENU);
            }
            if (e.getSource() == screen.getDeleteGame().getBtnDeleteGame()) {
                JOptionPane.showMessageDialog(screen, "Her kan du starte et spil"
                        , "Slet spil", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public class ShowHighScoreActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {


            if (e.getSource() == screen.getHighscore().getBtnHighscore()) {
//                Client client = Client.create();
//
//                WebResource webResource = client.resource("http://localhost:23000/api/scores/");
//
//                System.out.println("Der er nu forbindelse til serveren");
//                ClientResponse response = webResource.
//                        get(ClientResponse.class, "{\"id\", \"user_id"\, "game_id"\, "score"\, "opponent_id + "\"}");
//
//                //  post(ClientResponse.class, "{\"username\": \"" + Username + "\", \"password\": \"" + Password + "\"}");

            }

            if (e.getSource() == screen.getHighscore().getBtnMenu()) {
                screen.show(GUI.screen.USERMENU);
            }
        }
    }

    public class DeleteGameActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == screen.getDeleteGame().getBtnMenu()) {
                screen.show(GUI.screen.USERMENU);
            }
            if (e.getSource() == screen.getDeleteGame().getBtnDeleteGame()) {
                JOptionPane.showMessageDialog(screen, "Her kan du slette et spil"
                        , "Slet spil", JOptionPane.ERROR_MESSAGE);
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
                createGame.setName(screen.getCreateGame().getTextFieldCreateGame().getText());
                host.setControls(screen.getCreateGame().gettextFieldHostControls().getText());
                createGame.setHost(host);
                createGame.setOpponent(null);
                String message = api.createGame(createGame);

                JOptionPane.showMessageDialog(screen, "The game has been created"
                        , "Confirmation", JOptionPane.INFORMATION_MESSAGE);

                screen.show(GUI.screen.USERMENU);
            }
        }
    }

}