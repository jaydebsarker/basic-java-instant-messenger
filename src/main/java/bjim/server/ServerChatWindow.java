package bjim.server;

import bjim.common.AbstractChatWindow;

public class ServerChatWindow extends AbstractChatWindow {

    public ServerChatWindow() {
        this("Server");
    }

    public ServerChatWindow(String username) {
        super(username, "Client");
    }
}
