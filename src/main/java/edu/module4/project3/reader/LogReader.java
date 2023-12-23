package edu.module4.project3.reader;

import edu.module4.project3.model.DataLog;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LogReader {

    private static final String URL_PATTERN = "https?://.*";

    private LogReader() {
    }

    public static List<DataLog> readAllLogs(List<String> paths) {
        Objects.requireNonNull(paths);
        return paths.stream()
            .map(path -> {
                List<String> linesLogs = LogReader.readLogs(path);
                return new DataLog(path, linesLogs);
            }).toList();
    }

    public static List<String> readLogs(String path) {
        Objects.requireNonNull(path);
        List<String> logs = new ArrayList<>();
        try (var bufferedReader = new BufferedReader(getReader(path))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                logs.add(line);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return logs;
    }

    private static Reader getReader(String path) throws Exception {
        if (path.matches(URL_PATTERN)) {
            URL url = URI.create(path).toURL();
            return new InputStreamReader(url.openStream());
        } else {
            return new FileReader(path);
        }
    }
}
