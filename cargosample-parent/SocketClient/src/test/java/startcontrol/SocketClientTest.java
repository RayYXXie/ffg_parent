package startcontrol;

import static org.junit.Assert.*;

import org.junit.Test;

public class SocketClientTest {

	@Test
	public void testStart() {
		SocketClient client = new SocketClient();
		client.start();
	}

}
