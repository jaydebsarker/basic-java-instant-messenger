package bjim.server;

import bjim.common.AbstractChatWindow;

import javax.swing.text.BadLocationException;
import java.io.IOException;

public class ServerChatWindow extends AbstractChatWindow {

    public ServerChatWindow() throws IOException, BadLocationException {
        this("Server");
    }

    public ServerChatWindow(String username) throws IOException, BadLocationException {
        super(username);
    }

    public boolean isUserMessageVisible() {
        return userInput.isVisible();
    }

    public boolean abletowrite() {
        userInput.setEditable(false);
        return userInput.isEditable();
    }
}
