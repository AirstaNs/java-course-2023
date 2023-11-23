package edu.hw6.task6;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.net.ServerSocket;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PortScannerTest {

    @Test
    void testTCPPortOccupied() throws IOException {
        int testPort = 9999;

        try (ServerSocket serverSocket = new ServerSocket(testPort)) {
            PortScanner scanner = new PortScanner();
            PortInfo portInfo = scanner.scanPort(testPort, PortInfo.Protocol.TCP);

            assertEquals(PortInfo.Status.OPEN, portInfo.status());
        }
    }
}
