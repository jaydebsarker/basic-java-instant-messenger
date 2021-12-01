package bjim.client;

import lombok.Getter;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.List;

import static java.awt.BorderLayout.NORTH;
import static java.awt.Font.BOLD;

public class OnlineUsersWindow {

    private static final int FONT_SIZE = 18;
    private static final int WINDOW_WIDTH = 500;
    private static final int WINDOW_HEIGHT = 300;
    private static final String FONT_NAME = "Segoe Script";

    protected final JFrame chatWindow;
    protected final JLabel onlineUsersLabel;
    protected final JList<String> onlineUsersJList;

    @Getter
    private final String username;

    public OnlineUsersWindow(String username) {

        this.username = username;

        chatWindow = new JFrame(username);
        chatWindow.setLayout(new BorderLayout());

        onlineUsersLabel = new JLabel();
        onlineUsersLabel.setText("\tOnline Users");
        chatWindow.add(onlineUsersLabel, NORTH);

        onlineUsersJList = createOnlineUsersList();
        onlineUsersJList.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);

        chatWindow.add(onlineUsersJList);

        chatWindow.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        chatWindow.setVisible(true);
    }

    private JList<String> createOnlineUsersList() {
        JList<String> jList = new JList<>();
        Font font = new Font(FONT_NAME, BOLD, FONT_SIZE);
        jList.setFont(font);
        return jList;
    }

    public void onUsernameSelected(ListSelectionListener listSelectionListener) {
        onlineUsersJList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                listSelectionListener.valueChanged(e);
            }
        });
    }

    public void setOnlineUsersJList(String[] onlineUsers) {
        onlineUsersJList.setListData(onlineUsers);
    }
}
