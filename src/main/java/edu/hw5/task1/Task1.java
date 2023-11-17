package edu.hw5.task1;

import java.time.Duration;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

public final class Task1 {
    private static final String FORMAT = "yyyy-MM-dd, HH:mm";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(FORMAT);
    private static final IntervalParser INTERVAL_PARSER = new IntervalParser(FORMATTER);

    private Task1() {
    }

    public static String getFormattedAverageIntervals(List<String> intervals) {
        Objects.requireNonNull(intervals);
        double averageTime = calculateAverageTime(intervals);
        return formatDuration(averageTime);
    }

    private static double calculateAverageTime(List<String> intervals) {
        return intervals.stream()
            .map(INTERVAL_PARSER::parse)
            .map(Interval::between)
            .mapToLong(Duration::toMinutes)
            .average()
            .orElse(0.0);
    }

    private static String formatDuration(double average) {
        Duration duration = Duration.ofMinutes((long) average);
        long hours = duration.toHours();
        long minutes = duration.toMinutesPart();
        return String.format("%dч %dм", hours, minutes);
    }
}
