package edu.project3.model;

import java.util.Arrays;
import java.util.Objects;

public enum HttpMethod {
    GET, POST, PUT, DELETE, HEAD, OPTIONS, PATCH;

    public static HttpMethod parse(String s) {
        Objects.requireNonNull(s);
        String upperValue = s.strip().toUpperCase();
        return Arrays.stream(HttpMethod.values())
            .filter(format -> format.name().equals(upperValue))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("wrong HTTP method"));
    }
}
