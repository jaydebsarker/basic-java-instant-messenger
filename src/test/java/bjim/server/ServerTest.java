package bjim.server;

import static bjim.server.Server.DEFAULT_PORT;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ServerTest {

    private static final int WAIT_SECS = 500;

    private static final int CUSTOM_PORT = 1234;

    final ServerChatWindow serverChatWindow = mock(ServerChatWindow.class);

    Server server = new Server(serverChatWindow);

    @Before
    public void setUp() throws InterruptedException {
        server = new Server(serverChatWindow);
        server.startRunning();
        Thread.sleep(WAIT_SECS);
    }

    @After
    public void tearDown() throws InterruptedException {
        server.stopRunning();
        Thread.sleep(WAIT_SECS);
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
        Thread.sleep(5000);
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
        Server customServer = new Server(CUSTOM_PORT, serverChatWindow);

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
    }

    @Test
    public void windowIsVisibleDuringWhenStartTheServer() {

        // given
        when(serverChatWindow.isVisible()).thenReturn(true);

        // then
        assertTrue(server.isWindowVisible());
    }
}
