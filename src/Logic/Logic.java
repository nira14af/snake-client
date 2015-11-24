package Logic;

import GUI.screen;
import GUI.startGame;
import GUI.login;
import GUI.UserMenu;
import GUI.highscore;
import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import SDK.Api;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.ws.rs.*;
import com.sun.jersey.api.client.ClientResponse;
import javax.ws.rs.core.Response;


public class Logic {

    public static String currentUser;
    private GUI.screen screen;
    public startGame StartGame;
    public login login;
    public UserMenu userMenu;
    public highscore highscore;
    public Api api;


    public Logic() {
        screen = new screen();
        screen.setVisible(true);
    }


    public void run() {
        screen.getLogin().addActionListener(new LoginActionListener());
        screen.getUsermenu().addActionListener(new UsermenuActionListener());
        screen.getStartgame().addActionListener(new StartGameActionListener());
        screen.getHighscore().addActionListener(new ShowHighScoreActionListener());
        screen.getDeleteGame().addActionListener(new DeleteGameActionListener());
    }

    public class LoginActionListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {


            String Username = screen.getLogin().getUserNameInput().getText();
            String Password = screen.getLogin().getPasswordInput().getText();

            Client client = Client.create();

            WebResource webResource = client.resource("http://localhost:23000/api/login");

            ClientResponse response = webResource.
                    post(ClientResponse.class, "{\"username\": \"" + Username + "\", \"password\": \"" + Password + "\"}");

                if (e.getSource() == screen.getLogin().getBtnContinue()) {

                if (response.getStatus() == 200)
                {
                    screen.show(GUI.screen.USERMENU);
                    currentUser = Username;
                }
                if (response.getStatus() == 400)
                {
                    JOptionPane.showMessageDialog(screen, "Invalid Username or Password, please try again"
                            , "Invalid input", JOptionPane.ERROR_MESSAGE);

                }

                if (e.getSource() == screen.getLogin().getBtnShutDown())
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
                    Client client = Client.create();

                    WebResource webResource = client.resource("http://localhost:23000/api/scores/");

                    System.out.println("Der er nu forbindelse til serveren");
                    ClientResponse response = webResource.
                    get(ClientResponse.class, "{\"id\", \"user_id"\, "game_id"\, "score"\, "opponent_id + "\"}");

                    //  post(ClientResponse.class, "{\"username\": \"" + Username + "\", \"password\": \"" + Password + "\"}");

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
    }



