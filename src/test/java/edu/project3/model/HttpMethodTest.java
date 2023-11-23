package edu.project3.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class HttpMethodTest {

    @Test
    public void testParseGet() {
        assertEquals(HttpMethod.GET, HttpMethod.parse("GET"));
    }

    @Test
    public void testParsePost() {
        assertEquals(HttpMethod.POST, HttpMethod.parse("POST"));
    }

    @Test
    public void testInvalidMethod() {
        assertThrows(IllegalArgumentException.class, () -> HttpMethod.parse("INVALID"));
    }

    @Test
    public void testNullMethod() {
        assertThrows(NullPointerException.class, () -> HttpMethod.parse(null));
    }

    @Test
    public void testCaseInsensitiveParse() {
        assertEquals(HttpMethod.PUT, HttpMethod.parse("put"));
        assertEquals(HttpMethod.DELETE, HttpMethod.parse("Delete"));
    }
}
