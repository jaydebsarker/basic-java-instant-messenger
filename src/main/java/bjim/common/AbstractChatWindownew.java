package bjim.common;

import static java.awt.Font.BOLD;
import static javax.swing.SwingUtilities.invokeLater;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import lombok.Getter;

public class AbstractChatWindownew {

    private static final int FONT_SIZE = 18;
    private static final int WINDOW_WIDTH = 500;
    private static final int WINDOW_HEIGHT = 300;
    private static final String FONT_NAME = "Segoe Script";

    public JFrame chatWindow;
    public JTextField userInput;
    public JTextArea chatText;
    public JLabel status;
    private final JPanel contentPane;
    private final JButton btnSendFile;
    private final JProgressBar progressBar;
    JButton btnSend;

    @Getter public String username;

    public AbstractChatWindownew(String username) {

        this.username = username;

        status = new JLabel("");

        chatWindow = new JFrame(username);
        chatWindow.setResizable(false);

        chatWindow.setTitle(username);

        chatWindow.setBounds(100, 100, 576, 595);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        chatWindow.setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setBounds(0, 0, 573, 67);
        contentPane.add(panel);
        panel.setLayout(null);

        ImageIcon image2 =
                new ImageIcon(
                        "/Users/amarees/IdeaProjects/basic-java-instant-messenger/src/main/resources/image/profile.png");
        JLabel lblNewLabel = new JLabel(image2);
        lblNewLabel.setBounds(20, 0, 66, 67);
        panel.add(lblNewLabel);

        JPanel panel_6 = new JPanel();

        Font font = new Font(FONT_NAME, BOLD, FONT_SIZE);
        chatText = new JTextArea();
        chatText.setEditable(false);

        chatText.setFont(font);

        panel_6.setBounds(0, 66, 562, 323);
        panel_6.setLayout(null);
        JScrollPane scrollPane = new JScrollPane(chatText);
        scrollPane.setBounds(0, 0, 562, 323);
        panel_6.add(scrollPane);
        contentPane.add(panel_6);

        JPanel panel_2 = new JPanel();
        panel_2.setBounds(0, 372, 573, 73);
        contentPane.add(panel_2);
        panel_2.setLayout(null);

        ImageIcon image3 =
                new ImageIcon(
                        "/Users/amarees/IdeaProjects/basic-java-instant-messenger/src/main/resources/image/crying.png");
        JButton btnNewButton_2 = new JButton(image3);
        btnNewButton_2.setBounds(31, 22, 44, 41);
        btnNewButton_2.setBorder(new EmptyBorder(0, 0, 0, 0));
        btnNewButton_2.setContentAreaFilled(false);
        panel_2.add(btnNewButton_2);

        ImageIcon image4 =
                new ImageIcon(
                        "/Users/amarees/IdeaProjects/basic-java-instant-messenger/src/main/resources/image/heart_eye.png");
        JButton btnNewButton_4 = new JButton(image4);
        btnNewButton_4.setBounds(120, 22, 44, 41);
        btnNewButton_4.setBorder(new EmptyBorder(0, 0, 0, 0));
        btnNewButton_4.setContentAreaFilled(false);
        panel_2.add(btnNewButton_4);

        ImageIcon image5 =
                new ImageIcon(
                        "/Users/amarees/IdeaProjects/basic-java-instant-messenger/src/main/resources/image/sad.png");
        JButton btnNewButton_5 = new JButton(image5);

        btnNewButton_5.setBounds(265, 22, 44, 41);
        btnNewButton_5.setBorder(new EmptyBorder(0, 0, 0, 0));
        btnNewButton_5.setContentAreaFilled(false);
        panel_2.add(btnNewButton_5);

        ImageIcon image6 =
                new ImageIcon(
                        "/Users/amarees/IdeaProjects/basic-java-instant-messenger/src/main/resources/image/crying.png");
        JButton btnNewButton_6 = new JButton(image6);
        btnNewButton_6.setBounds(31 * 4, 22, 44, 41);
        btnNewButton_6.setBorder(new EmptyBorder(0, 0, 0, 0));
        btnNewButton_6.setContentAreaFilled(false);
        panel_2.add(btnNewButton_2);

        ImageIcon image7 =
                new ImageIcon(
                        "/Users/amarees/IdeaProjects/basic-java-instant-messenger/src/main/resources/image/scared.png");
        JButton btnNewButton_7 = new JButton(image7);
        btnNewButton_7.setBounds(378, 22, 44, 41);
        btnNewButton_7.setBorder(new EmptyBorder(0, 0, 0, 0));
        btnNewButton_7.setContentAreaFilled(false);
        panel_2.add(btnNewButton_7);

        progressBar = new JProgressBar();
        progressBar.setBounds(10, 22, 540, 41);
        progressBar.setVisible(false);
        panel_2.add(progressBar);

        JPanel panel_3 = new JPanel();
        panel_3.setBounds(0, 446, 562, 73);
        contentPane.add(panel_3);
        panel_3.setLayout(null);

        ImageIcon image8 =
                new ImageIcon(
                        "/Users/amarees/IdeaProjects/basic-java-instant-messenger/src/main/resources/image/send.png");
        btnSend = new JButton(image8);
        btnSend.setBorder(new EmptyBorder(0, 0, 0, 0));
        btnSend.setContentAreaFilled(false);
        btnSend.setBounds(498, 5, 64, 64);
        panel_3.add(btnSend);

        ImageIcon image9 =
                new ImageIcon(
                        "/Users/amarees/IdeaProjects/basic-java-instant-messenger/src/main/resources/image/document.png");
        btnSendFile = new JButton(image9);
        btnSendFile.setBorder(new EmptyBorder(0, 0, 0, 0));
        btnSendFile.setContentAreaFilled(false);

        btnSendFile.setBounds(440, 10, 64, 53);
        panel_3.add(btnSendFile);

        userInput = new JTextField();
        userInput.setBounds(0, 5, 433, 58);
        panel_3.add(userInput);
        userInput.setColumns(10);

        chatWindow.setVisible(true);
    }

    public void setStatus(String statusText) {
        status.setText(statusText);
    }

    public void onSend(ActionListener actionListener) {
        userInput.addActionListener(
                e -> {
                    actionListener.actionPerformed(e);
                    userInput.setText("");
                });
    }

    public void append(String s) {
        chatText.append(s);
    }

    public boolean isVisible() {
        return chatWindow.isVisible();
    }

    public void showMessage(final String text) {
        invokeLater(() -> chatText.append(text));
        invokeLater(() -> chatText.append("\n___________________________________"));
    }

    public void ableToType(final boolean tof) {
        invokeLater(() -> userInput.setEditable(tof));
    }

    public void setDefaultCloseOperation(int exitOnClose) {
        chatWindow.setDefaultCloseOperation(exitOnClose);
    }
}
