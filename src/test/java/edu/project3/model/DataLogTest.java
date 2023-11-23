package edu.project3.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

public class DataLogTest {

    private DataLog dataLog;
    private List<String> linesLog;

    @BeforeEach
    public void setup() {
        linesLog = Arrays.asList("line1", "line2", "line3");
        dataLog = new DataLog("path/to/log", linesLog);
    }

    @Test
    public void testConstructorAndGetter() {
        assertEquals("path/to/log", dataLog.getPath());
        assertEquals(linesLog, dataLog.getLinesLog());
        assertNull(dataLog.getNginxData());
    }
}
