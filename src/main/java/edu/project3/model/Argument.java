package edu.project3.model;

import java.util.Arrays;
import java.util.Objects;

public enum Argument {
    PATH, FROM, TO, FORMAT;

    public static final String PREFIX = "--";

    public static Argument parse(String value) {
        Objects.requireNonNull(value);
        String upperValue = value.strip().toUpperCase().replaceAll(PREFIX, "");
        return Arrays.stream(Argument.values())
            .filter(format -> format.name().equals(upperValue))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("wrong argument"));
    }
}
