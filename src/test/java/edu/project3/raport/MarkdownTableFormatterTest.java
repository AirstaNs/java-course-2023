package edu.project3.raport;

import static org.junit.jupiter.api.Assertions.*;
import edu.project3.raport.markdown.MarkdownTableFormatter;
import org.junit.jupiter.api.Test;
import java.util.List;

public class MarkdownTableFormatterTest {

    @Test
    public void testCreateTable() {
        MarkdownTableFormatter formatter = new MarkdownTableFormatter();
        List<String> headers = List.of("Header1", "Header2");
        List<List<String>> rows = List.of(
            List.of("Row1Col1", "Row1Col2"),
            List.of("Row2Col1", "Row2Col2")
        );

        String expected ="|:--------:|:--------:|";

        String result = formatter.createTable(headers, rows);
        assertTrue(result.contains(expected));
    }
}
