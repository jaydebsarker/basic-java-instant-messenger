package bjim.client;

import bjim.common.AbstractChatWindow;

import javax.swing.text.BadLocationException;
import java.io.IOException;

public class ClientChatWindow extends AbstractChatWindow {

    public ClientChatWindow() throws IOException, BadLocationException {
        this("Client");
    }

    public ClientChatWindow(String username) throws IOException, BadLocationException {
        super(username);
    }
}
