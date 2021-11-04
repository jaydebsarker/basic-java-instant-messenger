package bjim.server;

import static bjim.server.Server.DEFAULT_PORT;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import bjim.client.Client;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ServerTest {

    private static final int WAIT_SECS = 100;

    private static final int CUSTOM_PORT = 1234;

    Server server = new Server();

    @Before
    public void setUp() throws InterruptedException {
        server = new Server();
        server.startRunning();
        Thread.sleep(WAIT_SECS);
    }

    @After
    public void tearDown() {
        server.stopRunning();
    }

    @Test
    public void startServer() {

        // then
        assertTrue(server.isRunning());
    }

    @Test
    public void stopServer() throws InterruptedException {

        // when
        server.stopRunning();

        // then
        Thread.sleep(WAIT_SECS);
        assertFalse(server.isRunning());
    }

    @Test
    public void serverStartsOnDefaultPort() {

        // when..then
        assertEquals(DEFAULT_PORT, server.getPort());
    }

    @Test
    public void serverStartsOnCustomPort() throws InterruptedException {

        // given
        Server customServer = new Server(CUSTOM_PORT);

        // before
        assertNotEquals(
                "Precondition violated: `customPort` MUST NOT be equal to DEFAULT_PORT: "
                        + DEFAULT_PORT,
                DEFAULT_PORT,
                CUSTOM_PORT);

        // when
        customServer.startRunning();

        // then
        Thread.sleep(WAIT_SECS);
        assertEquals(CUSTOM_PORT, customServer.getPort());

        // after code
        customServer.stopRunning();
    }

    @Test
    public void numberOfConnectedClientsIsZero() {

        // then
        assertEquals(0, server.numberOfClientsConnected());

        // after
        server.stopRunning();
    }

    @Test
    public void serverSendsAMessageAndClientReceivesIt() throws InterruptedException {

        // given
        Client client = new Client("127.0.0.1");
        client.startRunning();
        Thread.sleep(WAIT_SECS);

        // when
        server.sendMessage("hi");
        Thread.sleep(500);

        // then
        assertEquals("Server:\n  hi", client.getLastReceivedMessage());

        // after
        server.stopRunning();
        client.stopRunning();
    }

    @Test
    public void serverUserMessageVisibleTrue() {

        // then
        assertEquals(true, server.isServerMessageVisible());

        // after
        server.stopRunning();
    }

    @Test
    public void windowIsVisibleDuringWhenStartTheServer() {

        // then
        assertEquals(true, server.isWindowVisible());

        // after
        server.stopRunning();
    }
}
