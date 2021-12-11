package bjim.client;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class UsernameWindow {

    JFrame usernameFrame = new JFrame("Login to Instant Messenger");

    private final JTextField usernameTextField = new JTextField();

    private final JButton enterServer = new JButton("Start Chat");

    public UsernameWindow() {

        usernameFrame.setBounds(100, 100, 450, 595);

        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        usernameFrame.setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewUserRegister = new JLabel("Enter your username, please");
        lblNewUserRegister.setFont(new Font("Tahoma", Font.PLAIN, 28));
        lblNewUserRegister.setBounds(20, 80, 400, 45);
        contentPane.add(lblNewUserRegister);

        JLabel lblName = new JLabel("Username");
        lblName.setFont(new Font("Tahoma", Font.PLAIN, 28));
        lblName.setBounds(20, 170, 150, 35);
        contentPane.add(lblName);

        usernameTextField.setFont(new Font("Tahoma", Font.PLAIN, 28));
        usernameTextField.setBounds(150, 160, 228, 50);
        contentPane.add(usernameTextField);
        usernameTextField.setColumns(10);

        enterServer.setFont(new Font("Tahoma", Font.PLAIN, 28));
        enterServer.setBounds(150, 235, 228, 40);
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
