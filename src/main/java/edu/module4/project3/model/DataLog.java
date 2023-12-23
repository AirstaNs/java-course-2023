package edu.module4.project3.model;

import edu.module4.project3.parser.LogParser;
import java.util.List;
import java.util.Objects;

public final class DataLog {
    private final String path;
    private final List<String> linesLog;

    private List<NginxData> nginxData;

    public DataLog(String path, List<String> linesLog) {
        Objects.requireNonNull(path);
        Objects.requireNonNull(linesLog);
        this.path = path;
        this.linesLog = linesLog;
    }

    public void parseLinesLog() {
        this.nginxData = linesLog
                    .stream()
                    .map(LogParser::parseLine)
                    .toList();
    }

    public String getPath() {
        return path;
    }

    public List<String> getLinesLog() {
        return linesLog;
    }

    public List<NginxData> getNginxData() {
        return nginxData;
    }
}
