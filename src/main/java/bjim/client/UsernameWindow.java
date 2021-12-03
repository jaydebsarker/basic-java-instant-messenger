package bjim.client;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class UsernameWindow {

    JFrame usernameFrame = new JFrame("Login to Instant Messenger");

    private final JTextField usernameTextField = new JTextField();

    private final JButton enterServer = new JButton("LOGIN");

    public UsernameWindow() {

        usernameFrame.setBounds(100, 100, 576, 595);

        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        usernameFrame.setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewUserRegister = new JLabel("User Login");
        lblNewUserRegister.setFont(new Font("Times New Roman", Font.BOLD, 40));
        lblNewUserRegister.setBounds(220, 80, 200, 45);
        contentPane.add(lblNewUserRegister);

        JLabel lblName = new JLabel("Username");
        lblName.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblName.setBounds(105, 160, 150, 35);
        contentPane.add(lblName);

        usernameTextField.setFont(new Font("Tahoma", Font.PLAIN, 32));
        usernameTextField.setBounds(220, 160, 228, 50);
        contentPane.add(usernameTextField);
        usernameTextField.setColumns(10);

        enterServer.setFont(new Font("Tahoma", Font.PLAIN, 20));
        enterServer.setBounds(220, 235, 228, 32);
        contentPane.add(enterServer);

        usernameFrame.setVisible(true);
    }

    public void setActionListener(ActionListener actionListener) {
        enterServer.addActionListener(actionListener);
    }

    public String getUsername() {
        return usernameTextField.getText();
    }

    public void setVisible(boolean visible) {
        usernameFrame.setVisible(visible);
    }
}
