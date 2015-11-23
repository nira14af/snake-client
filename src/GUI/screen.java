package GUI;

/**
 * Created by Nicolaj on 19/11/2015.
 */

        import java.awt.CardLayout;
        import java.awt.Color;
        import java.awt.Toolkit;

        import javax.swing.JFrame;
        import javax.swing.JPanel;
        import javax.swing.border.EmptyBorder;

public class screen extends JFrame {

    public static final String LOGIN = "login";
    public static final String USERMENU = "userMenu";
    public static final String STARTGAME = "startGame";
    public static final String HIGHSCORE = "highscore";
    public static final String DELETEGAME = "deleteGame";

    private JPanel contentPane;
    private login login;
    private UserMenu userMenu;
    private CardLayout c;
    private startGame startGame;
    private highscore highscore;
    private deleteGame deleteGame;


    public screen() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 335);
        contentPane = new JPanel();
        contentPane.setBackground(Color.LIGHT_GRAY);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new CardLayout(0, 0));
        setResizable(false);

        login = new login();
        login.setBackground(Color.LIGHT_GRAY);
        contentPane.add(login, LOGIN);

        userMenu = new UserMenu();
        contentPane.add(userMenu, USERMENU);

        startGame = new startGame();
        contentPane.add(startGame, STARTGAME);

        highscore = new highscore();
        contentPane.add(highscore, HIGHSCORE);

        deleteGame = new deleteGame();
        contentPane.add(deleteGame, DELETEGAME);

        c = (CardLayout) getContentPane().getLayout();
    }

    public login getLogin() {
        return login;
    }

    public UserMenu getUsermenu() {
        return userMenu;
    }

    public startGame getStartgame() {
        return startGame;
    }

    public highscore getHighscore() {
        return highscore;
    }

    public deleteGame getDeleteGame() {
        return deleteGame;
    }

    public void show(String card) {
        c.show(this.getContentPane(), card);
    }

}
