package bjim.common;

import static java.awt.Font.BOLD;
import static javax.swing.SwingUtilities.invokeLater;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import lombok.Getter;

public class AbstractChatWindow {

    private static final int FONT_SIZE = 18;
    private static final String FONT_NAME = "Segoe Script";
    private JFrame chatWindow;
    private JTextField userInput;
    private JLabel status;
    private HTMLDocument doc1;
    private JPanel contentPane;
    private JTextPane pan;
    private JProgressBar progressBar;
    private ImageIcon image4;
    private HTMLEditorKit kit;
    public Style style;

    private static final Map<String, String> EMOJIS = new HashMap<>();

    static {
        EMOJIS.put(":D", "image/emo.gif");
        EMOJIS.put(":'(", "image/cry1.gif");
        EMOJIS.put("<3)", "image/heart_eye.gif");
        EMOJIS.put("o.O", "image/angry.gif");
        EMOJIS.put(":(", "image/sad.gif");
        EMOJIS.put(":'D", "image/smile_cry.gif");
    }

    @Getter public String username;

    public AbstractChatWindow(String username) {

        this.username = username;

        status = new JLabel("");

        Font font = new Font(FONT_NAME, BOLD, FONT_SIZE);
        chatWindow = new JFrame(username);
        chatWindow.setResizable(false);
        chatWindow.setTitle(username);
        chatWindow.setBounds(100, 100, 576, 595);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        chatWindow.setContentPane(contentPane);
        contentPane.setLayout(null);

        //        JPanel panel = new JPanel();
        //        panel.setBackground(Color.LIGHT_GRAY);
        //        panel.setBounds(0, 0, 573, 67);
        //        contentPane.add(panel);
        //        panel.setLayout(null);

        //        ImageIcon image2 = new ImageIcon("image/profile.png");
        //        JLabel lblNewLabel = new JLabel(image2);
        //        lblNewLabel.setBounds(20, 0, 66, 67);
        //        panel.add(lblNewLabel);

        JPanel panel_6 = new JPanel();

        String bodyRule =
                "body { font-family: " + FONT_NAME + "; " + "font-size: " + FONT_SIZE + "pt; }";

        pan = new JTextPane();
        pan.setEditable(false);
        kit = new HTMLEditorKit();
        doc1 = new HTMLDocument();
        Font font1 = new Font("Serif", Font.ITALIC, 18);
        pan.setFont(font1);
        pan.setEditorKit(kit);
        pan.setDocument(doc1);
        ((HTMLDocument) pan.getDocument()).getStyleSheet().addRule(bodyRule);

        panel_6.setBounds(0, 66, 562, 323);
        panel_6.setLayout(null);

        JScrollPane scrollPane = new JScrollPane(pan);
        scrollPane.setBounds(0, 0, 562, 323);
        panel_6.add(scrollPane);
        contentPane.add(panel_6);

        JPanel panel_2 = new JPanel();
        panel_2.setBounds(0, 372, 573, 73);
        contentPane.add(panel_2);
        panel_2.setLayout(null);

        ImageIcon image3 = new ImageIcon("image/crying.png");
        JButton btnNewButton_2 = new JButton(image3);
        btnNewButton_2.setBounds(31, 22, 44, 41);
        btnNewButton_2.setBorder(new EmptyBorder(0, 0, 0, 0));
        btnNewButton_2.setContentAreaFilled(false);
        panel_2.add(btnNewButton_2);

        btnNewButton_2.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        userInput.setText(":'(");
                    }
                });

        image4 = new ImageIcon("image/heart_eye.png");
        JButton btnNewButton_4 = new JButton(image4);
        btnNewButton_4.setBounds(31 * 3, 22, 44, 41);
        btnNewButton_4.setBorder(new EmptyBorder(0, 0, 0, 0));
        btnNewButton_4.setContentAreaFilled(false);
        panel_2.add(btnNewButton_4);

        btnNewButton_4.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        userInput.setText("<3)");
                    }
                });

        ImageIcon imagesmile = new ImageIcon("image/smile_big.png");
        JButton btnNewButton_8 = new JButton(imagesmile);

        btnNewButton_8.setBounds(31 * 5, 22, 44, 41);
        btnNewButton_8.setBorder(new EmptyBorder(0, 0, 0, 0));
        btnNewButton_8.setContentAreaFilled(false);
        panel_2.add(btnNewButton_8);

        btnNewButton_8.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        userInput.setText(":D");
                    }
                });

        ImageIcon image5 = new ImageIcon("image/sad.png");
        JButton btnNewButton_5 = new JButton(image5);

        btnNewButton_5.setBounds(31 * 7, 22, 44, 41);
        btnNewButton_5.setBorder(new EmptyBorder(0, 0, 0, 0));
        btnNewButton_5.setContentAreaFilled(false);
        panel_2.add(btnNewButton_5);

        btnNewButton_5.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        userInput.setText(":(");
                    }
                });

        ImageIcon image6 = new ImageIcon("image/angry.png");
        JButton btnNewButton_6 = new JButton(image6);
        btnNewButton_6.setBounds(31 * 9, 22, 44, 41);
        btnNewButton_6.setBorder(new EmptyBorder(0, 0, 0, 0));
        btnNewButton_6.setContentAreaFilled(false);
        panel_2.add(btnNewButton_6);

        btnNewButton_6.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        userInput.setText("o.O");
                    }
                });

        ImageIcon image7 = new ImageIcon("image/smile_cry.png");
        JButton btnNewButton_7 = new JButton(image7);
        btnNewButton_7.setBounds(31 * 11, 22, 44, 41);
        btnNewButton_7.setBorder(new EmptyBorder(0, 0, 0, 0));
        btnNewButton_7.setContentAreaFilled(false);
        btnNewButton_7.setText("\uD83C\uDFBC");
        panel_2.add(btnNewButton_7);

        btnNewButton_7.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        userInput.setText(":'D");
                    }
                });

        progressBar = new JProgressBar();
        progressBar.setBounds(10, 22, 540, 41);
        progressBar.setVisible(false);
        panel_2.add(progressBar);

        JPanel panel_3 = new JPanel();
        panel_3.setBounds(0, 446, 562, 73);
        contentPane.add(panel_3);
        panel_3.setLayout(null);

        //        ImageIcon image8 = new ImageIcon("image/send.png");
        //        btnSend = new JButton(image8);
        //        btnSend.setBorder(new EmptyBorder(0, 0, 0, 0));
        //        btnSend.setContentAreaFilled(false);
        //        btnSend.setBounds(498, 5, 64, 64);
        //        panel_3.add(btnSend);

        //        ImageIcon image9 = new ImageIcon("image/document.png");
        //        btnSendFile = new JButton(image9);
        //        btnSendFile.setBorder(new EmptyBorder(0, 0, 0, 0));
        //        btnSendFile.setContentAreaFilled(false);
        //        btnSendFile.setBounds(440, 10, 64, 53);
        //        panel_3.add(btnSendFile);

        userInput = new JTextField();
        userInput.setBounds(0, 5, 433, 58);
        panel_3.add(userInput);
        userInput.setColumns(10);

        JPanel statusPanel = new JPanel();

        statusPanel.setBounds(0, 520, 575, 30);
        statusPanel.setBackground(Color.green);

        statusPanel.add(status);
        contentPane.add(statusPanel);

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
        pan.setText(s);
    }

    public boolean isVisible() {
        return chatWindow.isVisible();
    }

    public void showMessage(final String text) {

        int lastIndexOfNewLine = text.lastIndexOf("\n");
        String path = EMOJIS.get(text.substring(lastIndexOfNewLine).trim());

        if (path != null) {
            String message = text.substring(0, lastIndexOfNewLine + 2);
            invokeLater(
                    () -> {
                        try {

                            doc1.insertString(doc1.getLength(), message, style);

                            kit.insertHTML(
                                    doc1,
                                    doc1.getLength(),
                                    "<img src=\"file:"
                                            + path
                                            + "\" alt=\"some_text\" width=\"100\" height=\"100\">",
                                    0,
                                    0,
                                    HTML.Tag.IMG);

                        } catch (BadLocationException | IOException e) {
                            e.printStackTrace();
                        }
                    });
        } else {
            invokeLater(
                    () -> {
                        try {
                            doc1.insertString(doc1.getLength(), text, style);
                        } catch (BadLocationException e) {
                            e.printStackTrace();
                        }
                    });
        }
    }

    public void ableToType(final boolean tof) {
        invokeLater(() -> userInput.setEditable(tof));
    }

    public void setDefaultCloseOperation(int exitOnClose) {
        chatWindow.setDefaultCloseOperation(exitOnClose);
    }
}
