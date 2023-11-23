package edu.project3.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.time.OffsetDateTime;

public class NginxDataTest {

    @Test
    public void testNginxDataCreation() {
        OffsetDateTime dateTime = OffsetDateTime.now();
        NginxData data = new NginxData("127.0.0.1", dateTime, HttpMethod.GET, "/index.html", "HTTP/1.1", 200, 512);

        assertEquals("127.0.0.1", data.ipAddress());
        assertEquals(dateTime, data.dateTime());
        assertEquals(HttpMethod.GET, data.method());
        assertEquals("/index.html", data.requestUri());
        assertEquals("HTTP/1.1", data.httpVersion());
        assertEquals(200, data.statusCode());
        assertEquals(512, data.bytes());
    }
}
