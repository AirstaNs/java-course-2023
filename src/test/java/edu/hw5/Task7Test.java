package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task7Test {

    private static Stream<Arguments> containsAtLeastThreeZerosData() {
        return Stream.of(
            Arguments.of("101010", false),
            Arguments.of("101", false),
            Arguments.of("110011", true),
            Arguments.of("10101", false)
        );
    }

    private static Stream<Arguments> startsAndEndsWithSameSymbolData() {
        return Stream.of(
            Arguments.of("101010", false),
            Arguments.of("110011", true),
            Arguments.of("10101", true)
        );
    }

    private static Stream<Arguments> hasLengthBetweenOneAndThreeData() {
        return Stream.of(
            Arguments.of("101010", false),
            Arguments.of("101", true),
            Arguments.of("10101", false)
        );
    }

    @ParameterizedTest
    @MethodSource("containsAtLeastThreeZerosData")
    @DisplayName("containsAtLeastThreeZeros")
    public void testContainsAtLeastThreeZeros(String input, boolean expected) {
        // When
        boolean result = Task7.containsAtLeastThreeZeros(input);

        // Then
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @MethodSource("startsAndEndsWithSameSymbolData")
    @DisplayName("startsAndEndsWithSameSymbol")
    public void testStartsAndEndsWithSameSymbol(String input, boolean expected) {
        // When
        boolean result = Task7.startsAndEndsWithSameSymbol(input);

        // Then
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @MethodSource("hasLengthBetweenOneAndThreeData")
    @DisplayName("hasLengthBetweenOneAndThree")
    public void testHasLengthBetweenOneAndThree(String input, boolean expected) {
        // When
        boolean result = Task7.hasLengthBetweenOneAndThree(input);

        // Then
        assertEquals(expected, result);
    }
}
