package edu.project3.model;

import java.util.Arrays;
import java.util.Objects;

public enum Format {
    ADOC,
    MARKDOWN;

    public static Format parse(String value) {
        Objects.requireNonNull(value);
        String upperValue = value.strip().toUpperCase();
        return Arrays.stream(Format.values())
            .filter(format -> format.name().equals(upperValue))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("wrong format"));
    }
}
