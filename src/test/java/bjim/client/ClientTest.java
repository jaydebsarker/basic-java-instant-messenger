package bjim.client;

import static bjim.client.Client.LOCAL_HOST;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import bjim.server.Server;
import bjim.server.ServerChatWindow;
import java.util.ArrayList;
import java.util.Set;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ClientTest {

    private static final int WAIT_SECS = 1000;

    final ServerChatWindow serverChatWindow = mock(ServerChatWindow.class);
    final ClientChatWindow clientChatWindow = mock(ClientChatWindow.class);
    final ClientChatWindow clientChatWindow2 = mock(ClientChatWindow.class);

    private Server server;
    private Client client;

    @Before
    public void setUp() throws InterruptedException {
        server = new Server(serverChatWindow);
        server.startRunning();
        Thread.sleep(WAIT_SECS);
        client = new Client(clientChatWindow);
        when(clientChatWindow.getUsername()).thenReturn("Client");
        when(serverChatWindow.getUsername()).thenReturn("Server");
    }

    @After
    public void tearDown() throws InterruptedException {
        server.stopRunning();
        Thread.sleep(WAIT_SECS);
    }

    @Test
    public void numberOfConnectedClientsIsOne() throws InterruptedException {

        // given
        client.startRunning();
        Thread.sleep(WAIT_SECS);

        // when...then
        assertEquals(1, server.numberOfClientsConnected());

        // after
        client.stopRunning();
    }

    @Test
    public void serverIPIsLocalHostByDefault() {

        // when
        String serverIP = client.getServerIP();

        // then
        assertEquals(LOCAL_HOST, serverIP);
    }

    @Test
    public void windowIsVisibleDuringStartTheClient() throws InterruptedException {

        // given
        when(clientChatWindow.isVisible()).thenReturn(true);
        client.startRunning();
        Thread.sleep(WAIT_SECS);

        // when...then
        assertTrue(client.isWindowVisibleClientSide());

        // after
        client.stopRunning();
    }

    @Test
    public void serverSendsAMessageAndClientReceivesIt() throws InterruptedException {

        // given
        client.startRunning();
        Thread.sleep(WAIT_SECS);

        // when
        server.sendMessage("hi");
        Thread.sleep(WAIT_SECS);

        // then
        assertEquals("Server:\n  hi", client.getLastReceivedMessage());

        // after
        client.stopRunning();
    }

    @Test
    public void clientSendsAMessageAndServerReceivesIt() throws InterruptedException {

        // given
        client.startRunning();
        Thread.sleep(WAIT_SECS);

        // when
        client.sendMessage("hi");
        Thread.sleep(WAIT_SECS);

        // then
        assertEquals("Client:\n  hi", server.getLastReceivedMessage());

        // after
        client.stopRunning();
    }

    @Test
    public void multipleClientsConnected() throws InterruptedException {

        // given
        Client client1 = new Client(clientChatWindow);
        Client client2 = new Client(clientChatWindow);

        // when
        client1.startRunning();
        client2.startRunning();
        Thread.sleep(WAIT_SECS);

        // then
        assertTrue(client1.isConnected());
        assertTrue(client2.isConnected());

        // after
        client1.stopRunning();
        client2.stopRunning();
    }

    @Test
    public void oneClientReceivesAListOfOnlineUsers() throws InterruptedException {

        // given
        client.startRunning();
        Thread.sleep(WAIT_SECS);

        // then
        Set<String> onlineUsers = client.getOnlineUsers();
        assertEquals(1, onlineUsers.size());
        assertEquals("Client", new ArrayList<>(onlineUsers).get(0));

        // after
        client.stopRunning();
    }

    @Test
    public void twoClientsReceiveAListOfOnlineUsers() throws InterruptedException {

        // given
        when(clientChatWindow2.getUsername()).thenReturn("Client2");

        Client client1 = new Client(clientChatWindow);
        Client client2 = new Client(clientChatWindow2);

        // when
        client1.startRunning();
        client2.startRunning();
        Thread.sleep(WAIT_SECS);

        // then
        Set<String> onlineUsers1 = client1.getOnlineUsers();
        assertEquals(2, onlineUsers1.size());
        assertEquals("Client2", new ArrayList<>(onlineUsers1).get(0));
        assertEquals("Client", new ArrayList<>(onlineUsers1).get(1));

        Set<String> onlineUsers2 = client1.getOnlineUsers();
        assertEquals(2, onlineUsers2.size());
        assertEquals("Client2", new ArrayList<>(onlineUsers2).get(0));
        assertEquals("Client", new ArrayList<>(onlineUsers2).get(1));

        // after
        client1.stopRunning();
        client2.stopRunning();
    }

    @Test
    public void oneReceiveAListOfOnlineUsersAfterAnotherDisconnects() throws InterruptedException {

        // given
        when(clientChatWindow2.getUsername()).thenReturn("Client2");

        Client client1 = new Client(clientChatWindow);
        Client client2 = new Client(clientChatWindow2);

        // when
        client1.startRunning();
        client2.startRunning();
        Thread.sleep(WAIT_SECS);

        // then
        Set<String> onlineUsers1 = client1.getOnlineUsers();
        assertEquals(2, onlineUsers1.size());
        assertEquals("Client2", new ArrayList<>(onlineUsers1).get(0));
        assertEquals("Client", new ArrayList<>(onlineUsers1).get(1));

        Set<String> onlineUsers2 = client2.getOnlineUsers();
        assertEquals(2, onlineUsers2.size());
        assertEquals("Client2", new ArrayList<>(onlineUsers2).get(0));
        assertEquals("Client", new ArrayList<>(onlineUsers2).get(1));

        // after
        client1.stopRunning();
        Thread.sleep(WAIT_SECS);

        onlineUsers2 = client2.getOnlineUsers();
        assertEquals(1, onlineUsers2.size());
        assertEquals("Client2", new ArrayList<>(onlineUsers2).get(0));

        client2.stopRunning();
    }

    @Test
    public void twoClientsSendMessagesToServer() throws InterruptedException {

        // given
        Client client1 = new Client(clientChatWindow);
        Client client2 = new Client(clientChatWindow);

        client1.startRunning();
        Thread.sleep(WAIT_SECS);

        client2.startRunning();
        Thread.sleep(WAIT_SECS);

        // when...then
        client1.sendMessage("hi");
        Thread.sleep(WAIT_SECS);
        assertEquals("Client:\n  hi", server.getLastReceivedMessage());

        client2.sendMessage("hello");
        Thread.sleep(WAIT_SECS);
        assertEquals("Client:\n  hello", server.getLastReceivedMessage());

        // after
        client1.stopRunning();
        client2.stopRunning();
    }

    @Test
    public void serverSendsMessagesToTwoClients() throws InterruptedException {

        // given
        Client client1 = new Client(clientChatWindow);
        Client client2 = new Client(clientChatWindow);
        client1.startRunning();
        client2.startRunning();
        Thread.sleep(WAIT_SECS);

        // when
        server.sendMessage("hi");

        // then
        Thread.sleep(WAIT_SECS);
        assertEquals("Server:\n  hi", client1.getLastReceivedMessage());
        assertEquals("Server:\n  hi", client2.getLastReceivedMessage());

        // after
        client1.stopRunning();
        client2.stopRunning();
    }
}
