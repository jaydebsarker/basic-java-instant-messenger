package bjim.client;

import bjim.common.*;

public class ClientChatWindow extends AbstractChatWindownew {

    public ClientChatWindow() {
        this("Client");
    }

    public ClientChatWindow(String username) {
        super(username);
    }
}
