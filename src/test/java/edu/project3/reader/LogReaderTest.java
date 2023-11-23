package edu.project3.reader;

import static org.junit.jupiter.api.Assertions.*;
import edu.project3.model.DataLog;
import org.junit.jupiter.api.Test;
import java.util.List;

public class LogReaderTest {

    @Test
    public void testReadAllLogsFromFile() {
        List<String> paths = List.of("src/main/resources/nginx_logs.txt");
        List<DataLog> dataLogs = LogReader.readAllLogs(paths);

        assertFalse(dataLogs.isEmpty());
        assertEquals(paths.size(), dataLogs.size());
    }

    @Test
    public void testReadAllLogsFromInvalidPath() {
        List<String> paths = List.of("invalid/path/to/log_not_exist.txt");
        assertThrows(RuntimeException.class, () -> LogReader.readAllLogs(paths));
    }
}
