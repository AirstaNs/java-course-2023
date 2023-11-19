package edu.project3.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class HttpStatusCodeTest {

    @Test
    public void testParseContinue() {
        assertEquals(HttpStatusCode.CONTINUE, HttpStatusCode.parse(100));
    }

    @Test
    public void testParseOk() {
        assertEquals(HttpStatusCode.OK, HttpStatusCode.parse(200));
    }

    @Test
    public void testParseNotFound() {
        assertEquals(HttpStatusCode.NOT_FOUND, HttpStatusCode.parse(404));
    }

    @Test
    public void testInvalidStatusCode() {
        assertThrows(IllegalArgumentException.class, () -> HttpStatusCode.parse(999));
    }

    @Test
    public void testBoundaryStatusCode() {
        assertEquals(HttpStatusCode.CONTINUE, HttpStatusCode.parse(100));
        assertEquals(HttpStatusCode.NETWORK_AUTHENTICATION_REQUIRED, HttpStatusCode.parse(511));
    }
}
