package bjim.server;

import bjim.common.Connection;
import bjim.common.MessageParser;
import bjim.common.MessageParser.Message;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@RequiredArgsConstructor
public class Server {

    public static final int DEFAULT_PORT = 6789;
    public static final String ANONYMOUS = "Anonymous";

    // the port where the server is listening
    private final int port;

    // the socket where the server is listening
    private ServerSocket serverSocket;

    private final ServerChatWindow chatWindow;

    private final Map<Connection, String> connections = new ConcurrentHashMap<>();

    // checking last received message from client to server
    @Getter private String lastReceivedMessage = "";

    // A single thread for the server accept loop
    private final ExecutorService serverThreadPool = Executors.newSingleThreadExecutor();

    private final ExecutorService handlerThreadPool = Executors.newFixedThreadPool(10);

    public Server() {
        this(DEFAULT_PORT);
    }

    public Server(int port) {
        this(port, new ServerChatWindow());
    }

    public Server(ServerChatWindow chatWindow) {
        this(DEFAULT_PORT, chatWindow);
    }

    public boolean isWindowVisible() {
        return chatWindow.isVisible();
    }

    public void startRunning() {
        chatWindow.onSend(event -> sendMessage(event.getActionCommand()));
        serverThreadPool.submit(new StartServer());
    }

    public synchronized void sendMessage(String message) {

        String messageToSend = chatWindow.getUsername() + ":\n  " + message;

        for (Connection connection : connections.keySet()) {
            doSendMessage(messageToSend, connection);
        }
    }

    public synchronized void broadcastControlMessage(String message) {
        for (Connection connection : connections.keySet()) {
            try {
                sendMessage(message, connection);
            } catch (IOException ioException) {
                chatWindow.setStatus("Failed to broadcast control message");
            }
        }
    }

    private void doSendMessage(String messageToSend, Connection connection) {
        try {
            sendMessage(messageToSend, connection);
            showMessage(messageToSend);
        } catch (IOException ioException) {
            chatWindow.setStatus("Failed to send message");
        }
    }

    private void sendMessage(String messageToSend, Connection connection) throws IOException {
        connection.getOutput().writeObject(messageToSend);
        connection.getOutput().flush();
    }

    public void stopRunning() {
        System.out.println("Stopping server...");
        while (serverSocket != null && !serverSocket.isClosed()) {
            try {
                serverSocket.close();
                return;
            } catch (IOException e) {
                System.out.println("Failed to stop server...");
            }
        }
    }

    public synchronized void showMessage(String text) throws IOException {
        chatWindow.showMessage(text);
    }

    public void ableToType(boolean tof) {
        chatWindow.ableToType(tof);
    }

    public int getPort() {
        return serverSocket.getLocalPort();
    }

    public boolean isRunning() {
        if (serverSocket == null) {
            return false;
        }
        return !serverSocket.isClosed();
    }

    public void setDefaultCloseOperation(int exitOnClose) {
        chatWindow.setDefaultCloseOperation(exitOnClose);
    }

    public int numberOfClientsConnected() {
        int count = 0;
        for (Connection connection : connections.keySet()) {
            if (connection != null && !connection.getSocket().isClosed()) {
                ++count;
            }
        }
        return count;
    }

    private class StartServer implements Runnable {

        @SneakyThrows
        @Override
        public void run() {
            try {
                serverSocket = new ServerSocket(port, 100);
                setStatus("Waiting for clients to connect!");
                ableToType(true);

                //noinspection InfiniteLoopStatement
                while (true) {
                    waitForConnection();
                }

            } catch (IOException ioException) {
                System.out.println("Stopping server: " + ioException.getMessage());
            } finally {
                disconnectClients();
            }
        }

        private void waitForConnection() throws IOException {

            Connection connection = new Connection(serverSocket.accept());
            addConnection(connection, ANONYMOUS);

            handlerThreadPool.submit(() -> readMessages(connection));

            setStatus("(" + connections.size() + ") client(s) are connected");
        }

        private void disconnectClients() {
            setStatus("Closing connections");
            ableToType(false);

            for (Connection connection : connections.keySet()) {
                closeClientConnection(connection);
            }
            connections.clear();
        }

        private void readMessages(Connection connection) {

            while (connection != null && connection.getInput() != null) {
                try {
                    lastReceivedMessage = String.valueOf(connection.getInput().readObject());

                    if (lastReceivedMessage.startsWith("username:")) {
                        String username = lastReceivedMessage.split(":")[1];
                        addConnection(connection, username);
                    } else {

                        Message message = MessageParser.parse(lastReceivedMessage);

                        if (message.isSelfMessage()) {
                            continue;
                        }
                        connections.entrySet().stream()
                                .filter(
                                        entry ->
                                                entry.getValue()
                                                        .equals(message.getTargetUsername()))
                                .findFirst()
                                .ifPresent(
                                        entry -> doSendMessage(message.getMsg(), entry.getKey()));
                    }
                } catch (IOException e) {
                    closeClientConnection(connection);
                    setStatus("(" + connections.size() + ") client(s) are connected");
                    break;
                } catch (ClassNotFoundException e) {
                    setStatus("(" + connections.size() + ") client(s) are connected");
                }
            }
        }

        private void closeClientConnection(Connection connection) {
            if (connection == null) {
                return;
            }
            try {
                connection.close();
                removeConnection(connection);
            } catch (IOException e) {
                System.out.println(
                        "Error while attempting to close client connection: " + e.getMessage());
            }
        }
    }

    private void setStatus(String text) {
        chatWindow.setStatus(text);
    }

    private void removeConnection(Connection connection) {
        connections.remove(connection);
        broadcastControlMessage("users:" + getAllUsernames());
    }

    private void addConnection(Connection connection, String username) {
        connections.put(connection, username);
        broadcastControlMessage("users:" + getAllUsernames());
    }

    public String getAllUsernames() {
        return connections.values().stream()
                .reduce((s1, s2) -> s1.concat(",").concat(s2))
                .orElse("");
    }
}
