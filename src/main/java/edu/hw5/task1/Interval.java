package edu.hw5.task1;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

public record Interval(LocalDateTime start, LocalDateTime end) {
    public Interval {
        this.validate(start, end);
    }

    private void validate(LocalDateTime start, LocalDateTime end) {
        Objects.requireNonNull(start);
        Objects.requireNonNull(end);
        if (start.isAfter(end)) {
            throw new IllegalArgumentException("start must come before end");
        }
    }

    public Duration between() {
        return Duration.between(start, end);
    }
}
