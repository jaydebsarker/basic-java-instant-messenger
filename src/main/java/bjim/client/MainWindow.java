package bjim.client;

import static java.awt.BorderLayout.NORTH;
import static java.awt.Font.BOLD;
import static javax.swing.WindowConstants.HIDE_ON_CLOSE;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;
import lombok.Getter;

public class MainWindow {

    private static final int FONT_SIZE = 18;
    private static final int WINDOW_WIDTH = 500;
    private static final int WINDOW_HEIGHT = 300;
    private static final String FONT_NAME = "Segoe Script";

    protected final JFrame mainFrame;
    protected final JLabel onlineUsersLabel;
    protected final JList<String> onlineUsersJList;

    Map<String, ClientChatWindow> chatWindows = new HashMap<>();

    @Getter private final String username;

    private ActionListener actionListener;

    public MainWindow(String username) {

        this.username = username;

        mainFrame = new JFrame(username);
        mainFrame.setLayout(new BorderLayout());

        onlineUsersLabel = new JLabel();
        onlineUsersLabel.setText("\tOnline Users");
        mainFrame.add(onlineUsersLabel, NORTH);

        onlineUsersJList = createOnlineUsersList();
        onlineUsersJList.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);

        mainFrame.add(onlineUsersJList);

        mainFrame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        mainFrame.setVisible(true);
    }

    public void onSend(ActionListener actionListener) {
        this.actionListener = actionListener;
    }

    private JList<String> createOnlineUsersList() {
        JList<String> jList = new JList<>();
        Font font = new Font(FONT_NAME, BOLD, FONT_SIZE);
        jList.setFont(font);
        return jList;
    }

    public void onUsernameSelected() {

        onlineUsersJList.addMouseListener(
                new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        String userSelected = onlineUsersJList.getSelectedValue();
                        if (userSelected == null || userSelected.isEmpty()) {
                            return;
                        }
                        getClientChatWindow(userSelected);
                    }
                });
    }

    private ClientChatWindow getClientChatWindow(String userSelected) {
        ClientChatWindow clientChatWindow =
                chatWindows.computeIfAbsent(
                        userSelected,
                        targetUsername -> {
                            ClientChatWindow aChatWindow =
                                    new ClientChatWindow(username, targetUsername);
                            aChatWindow.setDefaultCloseOperation(HIDE_ON_CLOSE);
                            aChatWindow.onSend(actionListener);
                            return aChatWindow;
                        });
        clientChatWindow.setVisible(true);
        return clientChatWindow;
    }

    public void setOnlineUsersJList(String[] onlineUsers) {
        onlineUsersJList.setListData(onlineUsers);
    }

    public void setDefaultCloseOperation(int defaultCloseOperation) {
        mainFrame.setDefaultCloseOperation(defaultCloseOperation);
    }

    public void showMessage(String message) {
        String from = message.substring(0, message.indexOf(":\n"));
        ClientChatWindow clientChatWindow = getClientChatWindow(from);
        clientChatWindow.showMessage(message);
    }

    public void setStatus(String text) {
        // todo: implement
    }
}
