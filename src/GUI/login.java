package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class login extends JPanel {
    //Variabler til GUI deklareres
    public JTextField userNameInput;
    public JPasswordField passwordInput;
    private JButton btnContinue;
    private JButton btnShutDown;

    //JPanel for Login med ovenstående variabler
    public login() {

        setBackground(Color.LIGHT_GRAY);
        setLayout(null);

        JLabel lblLogin = new JLabel("Login");
        lblLogin.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblLogin.setBounds(206, 35, 57, 17);
        add(lblLogin);

        btnContinue = new JButton("Continue");
        btnContinue.setToolTipText("");
        btnContinue.setBounds(284, 230, 96, 23);
        add(btnContinue);

        btnShutDown = new JButton("Shut down");
        btnShutDown.setToolTipText("Closes BCBS");
        btnShutDown.setBounds(58, 230, 96, 23);
        add(btnShutDown);

        JLabel lblNewLabel = new JLabel("Username: ");
        lblNewLabel.setBounds(71, 105, 83, 16);
        add(lblNewLabel);

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setBounds(71, 155, 83, 14);
        add(lblPassword);

        userNameInput = new JTextField();
        userNameInput.setBackground(Color.WHITE);
        userNameInput.setColumns(10);
        userNameInput.setBounds(147, 105, 147, 23);
        add(userNameInput);

        passwordInput = new JPasswordField();
        passwordInput.setBackground(Color.WHITE);
        passwordInput.setBounds(147, 152, 147, 23);
        add(passwordInput);

        JLabel piclabel = new JLabel("");
        piclabel.setBounds(322, 13, 100, 91);
        add(piclabel);

    }

    //ActionListers til knapperne tilføjes
    public void addActionListener(ActionListener l) {
        btnContinue.addActionListener(l);
        btnShutDown.addActionListener(l);
    }

    public JTextField getUserNameInput() {
        return userNameInput;
    }

    public JPasswordField getPasswordInput() {
        return passwordInput;
    }

    public JButton getBtnContinue() {
        return btnContinue;
    }

    public JButton getBtnShutDown() {
        return btnShutDown;
    }
}