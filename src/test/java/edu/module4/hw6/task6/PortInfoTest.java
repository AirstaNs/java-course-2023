package edu.module4.hw6.task6;

import edu.module4.hw6.task6.PortInfo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PortInfoTest {
    private static final int TEST_PORT = 80;
    private static final PortInfo.Protocol TEST_PROTOCOL = PortInfo.Protocol.TCP;
    private static final PortInfo.Status TEST_STATUS = PortInfo.Status.OPEN;
    private static final String TEST_SERVICE = "HTTP";

    @Test
    void portInfo_ShouldCorrectlyHoldData() {
        PortInfo portInfo = new PortInfo(TEST_PORT, TEST_PROTOCOL, TEST_STATUS, TEST_SERVICE);

        assertEquals(TEST_PORT, portInfo.port());
        assertEquals(TEST_PROTOCOL, portInfo.protocol());
        assertEquals(TEST_STATUS, portInfo.status());
        assertEquals(TEST_SERVICE, portInfo.service());
    }
}
