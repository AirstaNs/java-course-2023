package edu.hw7.task2;

import java.util.stream.LongStream;

public final class FactorialCalculator {

    private FactorialCalculator() {
    }

    public static long factorial(int n) {
        checkNonNegative(n);
        return LongStream
                .rangeClosed(1, n)
                .unordered()
                .parallel()
                .reduce((a, b) -> a * b)
                .orElse(1);
    }

    private static void checkNonNegative(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("Number must be non-negative.");
        }
    }
}
