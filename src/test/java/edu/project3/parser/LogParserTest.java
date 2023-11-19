package edu.project3.parser;

import edu.project3.model.HttpMethod;
import edu.project3.model.NginxData;
import org.junit.jupiter.api.Test;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LogParserTest {

    @Test
    public void testValidLogLine() {
        String logLine = "192.168.1.1 - - [01/Jan/2023:10:00:00 +0000] \"GET /index.html HTTP/1.1\" 200 1024 \"-\"";
        NginxData expectedData = new NginxData(
            "192.168.1.1",
            OffsetDateTime.parse("01/Jan/2023:10:00:00 +0000",
                DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss Z", Locale.ENGLISH)),
            HttpMethod.GET,
            "/index.html",
            "HTTP/1.1",
            200,
            1024);

        NginxData actualData = LogParser.parseLine(logLine);

        assertEquals(expectedData.ipAddress(), actualData.ipAddress());
        assertEquals(expectedData.dateTime(), actualData.dateTime());
        assertEquals(expectedData.method(), actualData.method());
        assertEquals(expectedData.requestUri(), actualData.requestUri());
        assertEquals(expectedData.httpVersion(), actualData.httpVersion());
        assertEquals(expectedData.statusCode(), actualData.statusCode());
        assertEquals(expectedData.bytes(), actualData.bytes());
    }
    @Test
    public void testInvalidLogLine() {
        String invalidLogLine = "This is not a valid log line";
        assertThrows(IllegalArgumentException.class, () -> LogParser.parseLine(invalidLogLine));
    }
}
