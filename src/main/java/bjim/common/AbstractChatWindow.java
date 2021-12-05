package bjim.common;

import static javax.swing.SwingUtilities.invokeLater;

import bjim.client.ClientChatWindow;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import lombok.Getter;
import lombok.SneakyThrows;

public class AbstractChatWindow {

    private static final int FONT_SIZE = 18;
    private static final String FONT_NAME = "Segoe Script";
    private final JFrame chatFrame;
    private final JTextField userInput;
    private final JLabel status;
    private final HTMLDocument doc1;
    private final JPanel contentPane;
    private final JTextPane pan;
    private final JProgressBar progressBar;
    private final ImageIcon image4;
    private final HTMLEditorKit kit;
    private final JButton  btnSendFile;
    public Style style;


    static BufferedImage image ;
  protected ByteArrayInputStream bis;
    static File file;
    static BufferedImage resized;



    private static final Map<String, String> EMOJIS = new HashMap<>();

    static {
        EMOJIS.put(":D", "image/emo.gif");
        EMOJIS.put(":'(", "image/cry1.gif");
        EMOJIS.put("<3)", "image/heart_eye.gif");
        EMOJIS.put("o.O", "image/angry.gif");
        EMOJIS.put(":(", "image/sad.gif");
        EMOJIS.put(":'D", "image/smile_cry.gif");
        EMOJIS.put("( ◕ᴗ◕✿ )", "image/rose.gif");
        EMOJIS.put("(* ﾟ∀ﾟ)", "image/clapping.gif");
        EMOJIS.put("(°ヘ°)", "image/confused.gif");
    }

    @Getter public final String username;

    @Getter public final String targetUser;

    public AbstractChatWindow(String username, String targetUser) {


        this.username = username;
        this.targetUser = targetUser;

        status = new JLabel("");

        chatFrame = new JFrame(username);
        chatFrame.setResizable(false);
        chatFrame.setTitle(username + " - " + targetUser);
        chatFrame.setBounds(100, 100, 576, 595);
        contentPane = new JPanel();
       contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        chatFrame.setContentPane(contentPane);
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



        String bodyRule =
                "body { font-family: " + FONT_NAME + "; " + "font-size: " + FONT_SIZE + "pt; }";

        pan = new JTextPane();
        pan.setAutoscrolls(true);
        pan.setMargin( new Insets(5,10,7,7) );
        pan.setEditable(false);
        kit = new HTMLEditorKit();
        doc1 = new HTMLDocument();
        Font font1 = new Font("Serif", Font.ITALIC, 18);
        pan.setFont(font1);
        pan.setEditorKit(kit);
        pan.setDocument(doc1);
        ((HTMLDocument) pan.getDocument()).getStyleSheet().addRule(bodyRule);

        JPanel panel_6 = new JPanel();

        panel_6.setBounds(0, 66, 562, 323);
        panel_6.setBackground(Color.red);
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
                        userInputAction();
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
                        userInputAction();
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
                        userInputAction();
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
                        userInputAction();
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
                        userInputAction();
                    }
                });

        ImageIcon image7 = new ImageIcon("image/smile_cry.png");
        JButton btnNewButton_7 = new JButton(image7);
        btnNewButton_7.setBounds(31 * 11, 22, 44, 41);
        btnNewButton_7.setBorder(new EmptyBorder(0, 0, 0, 0));
        btnNewButton_7.setContentAreaFilled(false);

        panel_2.add(btnNewButton_7);

        btnNewButton_7.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        userInput.setText(":'D");
                        userInputAction();
                    }
                });


        ImageIcon flower = new ImageIcon("image/rose.png");
        JButton btnNewButton_f = new JButton(flower);
        btnNewButton_f.setBounds(31 * 13, 22, 44, 41);
        btnNewButton_f.setBorder(new EmptyBorder(0, 0, 0, 0));
        btnNewButton_f.setContentAreaFilled(false);

        panel_2.add(btnNewButton_f);

        btnNewButton_f.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        userInput.setText("( ◕ᴗ◕✿ )");
                        userInputAction();
                    }
                });
        ImageIcon clap = new ImageIcon("image/clapping.png");
        JButton clapping= new JButton(clap);
        clapping.setBounds(31 * 15, 22, 44, 41);
        clapping.setBorder(new EmptyBorder(0, 0, 0, 0));
        clapping.setContentAreaFilled(false);

        panel_2.add(clapping);

        clapping.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        userInput.setText("(* ﾟ∀ﾟ)");
                        userInputAction();
                    }
                });
        ImageIcon confused = new ImageIcon("image/confusing.png");
        JButton confusion = new JButton(confused);
        confusion.setBounds(31 * 17, 24, 32, 32);
        confusion.setBorder(new EmptyBorder(0, 0, 0, 0));
        confusion.setContentAreaFilled(false);

        panel_2.add(confusion);

        confusion.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        userInput.setText("(°ヘ°)");
                        userInputAction();
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

              ImageIcon image9 = new ImageIcon("image/document.png");
               btnSendFile = new JButton(image9);
              btnSendFile.setBorder(new EmptyBorder(0, 0, 0, 0));
             btnSendFile.setContentAreaFilled(false);
             btnSendFile.setBounds(510, 10, 64, 53);
              panel_3.add(btnSendFile);


        btnSendFile.addActionListener(new ActionListener() {
            @SneakyThrows
            @Override
            public void actionPerformed(ActionEvent e) {

                JFileChooser jFileChooser = new JFileChooser();

                jFileChooser.setDialogTitle("Choose a file to send.");

                if (jFileChooser.showOpenDialog(null)  == JFileChooser.APPROVE_OPTION) {



               file=jFileChooser.getSelectedFile();
                    String fileName = file.getName();
                    BufferedImage     targetImg = ImageIO.read(file);
                  resized = resize(targetImg, 100, 300);
                    userInput.setText(fileName );







                }
            }
        });




        userInput = new JTextField();
        userInput.setBounds(0, 5, 505, 58);
        panel_3.add(userInput);
        userInput.setColumns(10);

        JPanel statusPanel = new JPanel();

        statusPanel.setBounds(0, 520, 575, 30);
        statusPanel.setBackground(Color.lightGray);

        statusPanel.add(status);
        contentPane.add(statusPanel);

        if (this instanceof ClientChatWindow) {
            chatFrame.setVisible(true);
        }
    }

    private void userInputAction() {
        userInput.requestFocusInWindow();
        try {
            new Robot().keyPress(KeyEvent.VK_ENTER);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
    private static BufferedImage resize(BufferedImage img, int height, int width) {
        int h= img.getHeight();

        int w=img.getWidth();
        if( w>300 && h>100 )
        {
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;}
        else return img;
    }

    public void setStatus(String statusText) {
        status.setText(statusText);
    }

    public void onSend(ActionListener actionListener) {
        userInput.addActionListener(
                e -> {
                    actionListener.actionPerformed(
                            new ActionEvent(
                                    e.getSource(),
                                    e.getID(),
                                    "to:" + getTargetUser() + ":\n  " + e.getActionCommand()));
                    userInput.setText("");
                });
    }

    public void setVisible(boolean b) {
        chatFrame.setVisible(true);
    }

    public boolean isVisible() {
        return chatFrame.isVisible();
    }

    public void showMessage(String inputText) throws IOException {

        final String text = "\n\n" + inputText;

        int lastIndexOfNewLine = text.lastIndexOf(":\n") + 3;
        String path = EMOJIS.get(text.substring(lastIndexOfNewLine).trim());
        System.out.println(text.substring(lastIndexOfNewLine).trim());





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
            }

            else {


                String checkextensions = text.substring(text.length() - 4);



                if (checkextensions.equals(".png")||checkextensions.equals(".jpg")) {
                    String sendername = text.substring(0, lastIndexOfNewLine + 2);


                    invokeLater(
                            () -> {
                                try {
                                    doc1.insertString(doc1.getLength(), sendername, style);
                                    doc1.insertString(doc1.getLength(), "", style);
                                    pan.insertIcon(new ImageIcon(resized));
                                } catch (BadLocationException e) {
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

    }


    public void ableToType(final boolean tof) {
        invokeLater(() -> userInput.setEditable(tof));
    }

    public void setDefaultCloseOperation(int exitOnClose) {
        chatFrame.setDefaultCloseOperation(exitOnClose);
    }
}
