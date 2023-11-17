package edu.hw5.task1;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class IntervalParser {
    private static final String SPLIT_PATTERN = " - ";

    private final DateTimeFormatter formatter;

    public IntervalParser(DateTimeFormatter formatter) {
        this.formatter = formatter;
    }

    public Interval parse(String interval) {
        Objects.requireNonNull(interval);
        String[] parts = interval.split(SPLIT_PATTERN);
        LocalDateTime start = LocalDateTime.parse(parts[0], formatter);
        LocalDateTime end = LocalDateTime.parse(parts[1], formatter);
        return new Interval(start, end);
    }
}
