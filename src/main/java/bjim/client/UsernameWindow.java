package bjim.client;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class UsernameWindow {

    JFrame usernameFrame = new JFrame("Specify your username");

    private final JTextField usernameTextField = new JTextField();

    private final JButton enterServer = new JButton("Start Chat");

    public UsernameWindow() {

        usernameFrame.setBounds(450, 190, 1014, 597);

        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        usernameFrame.setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewUserRegister = new JLabel("Welcome!!!");
        lblNewUserRegister.setFont(new Font("Times New Roman", Font.BOLD, 42));
        lblNewUserRegister.setBounds(362, 52, 325, 50);
        contentPane.add(lblNewUserRegister);

        JLabel lblName = new JLabel("Enter your name");
        lblName.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblName.setBounds(362, 152, 150, 43);
        contentPane.add(lblName);

        usernameTextField.setFont(new Font("Tahoma", Font.PLAIN, 32));
        usernameTextField.setBounds(362, 230, 228, 50);
        contentPane.add(usernameTextField);
        usernameTextField.setColumns(10);

        enterServer.setFont(new Font("Tahoma", Font.PLAIN, 22));
        enterServer.setBounds(362, 350, 259, 74);
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
