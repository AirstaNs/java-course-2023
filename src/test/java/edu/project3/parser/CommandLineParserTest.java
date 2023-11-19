package edu.project3.parser;

import static org.junit.jupiter.api.Assertions.*;
import edu.project3.model.Config;
import edu.project3.model.Format;
import org.junit.jupiter.api.Test;
import java.util.List;

public class CommandLineParserTest {

    @Test
    public void testValidArguments() {
        String[] args = {"--path", "path1", "path2", "--from", "2023-01-01", "--to", "2023-01-31", "--format", "markdown"};
        Config config = CommandLineParser.parse(args);

        assertEquals(List.of("path1", "path2"), config.getPaths());
        assertEquals("2023-01-01", config.getFromDate().toString());
        assertEquals("2023-01-31", config.getToDate().toString());
        assertEquals(Format.MARKDOWN, config.getFormat());
    }

    @Test
    public void testMissingArguments() {
        String[] args = {"--path", "path1", "--format"};
        assertThrows(IllegalArgumentException.class, () -> CommandLineParser.parse(args));
    }

    @Test
    public void testInvalidArgument() {
        String[] args = {"--invalid", "path1"};
        assertThrows(IllegalArgumentException.class, () -> CommandLineParser.parse(args));
    }
}
