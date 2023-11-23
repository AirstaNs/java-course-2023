package edu.project3.raport;

import static org.junit.jupiter.api.Assertions.*;
import edu.project3.raport.adoc.AdocTableFormatter;
import org.junit.jupiter.api.Test;
import java.util.List;

public class AdocTableFormatterTest {

    @Test
    public void testCreateTable() {
        AdocTableFormatter formatter = new AdocTableFormatter();
        List<String> headers = List.of("Header1", "Header2");
        List<List<String>> rows = List.of(
            List.of("Row1Col1", "Row1Col2"),
            List.of("Row2Col1", "Row2Col2")
        );

        String expected = "|===";

        String result = formatter.createTable(headers, rows);
        assertTrue(result.contains(expected));
    }
}
