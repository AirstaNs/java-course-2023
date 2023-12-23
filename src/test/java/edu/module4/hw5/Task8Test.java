package edu.module4.hw5;

import edu.module4.hw5.Task8;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task8Test {

    private static Stream<Arguments> task1Data() {
        return Stream.of(
            Arguments.of("0", true),
            Arguments.of("1", true),
            Arguments.of("00", false),
            Arguments.of("01", false),
            Arguments.of("010", true),
            Arguments.of("111", true)
        );
    }

    private static Stream<Arguments> task2Data() {
        return Stream.of(
            Arguments.of("0", true),
            Arguments.of("1", false),
            Arguments.of("00", false),
            Arguments.of("01", false),
            Arguments.of("010", true),
            Arguments.of("111", false)
        );
    }

    private static Stream<Arguments> task3Data() {
        return Stream.of(
            Arguments.of("000", true),
            Arguments.of("111", false),
            Arguments.of("0000", false),
            Arguments.of("00110", true),
            Arguments.of("1001", false),
            Arguments.of("0101", false)
        );
    }

    private static Stream<Arguments> task4Data() {
        return Stream.of(
            Arguments.of("11", false),
            Arguments.of("111", false),
            Arguments.of("000", true),
            Arguments.of("0011", true),
            Arguments.of("1001", true),
            Arguments.of("0101", true)
        );
    }

    private static Stream<Arguments> task5Data() {
        return Stream.of(
            Arguments.of("11", true),
            Arguments.of("111", true),
            Arguments.of("000", false),
            Arguments.of("0011", false),
            Arguments.of("1001", false),
            Arguments.of("0101", false)
        );
    }

    private static Stream<Arguments> task6Data() {
        return Stream.of(
            Arguments.of("110", false),
            Arguments.of("111", false),
            Arguments.of("000", true),
            Arguments.of("0011", false),
            Arguments.of("1001", false),
            Arguments.of("0101", false),
            Arguments.of("0100", true)
        );
    }

    private static Stream<Arguments> task7Data() {
        return Stream.of(
            Arguments.of("11", false),
            Arguments.of("111", false),
            Arguments.of("000", true),
            Arguments.of("0011", false),
            Arguments.of("1001", true),
            Arguments.of("0101", true),
            Arguments.of("0100", true)
        );
    }

    @ParameterizedTest
    @MethodSource("task1Data")
    @DisplayName("task1")
    public void testTaskOne(String input, boolean expected) {
        // When
        boolean result = Task8.task1(input);

        // Then
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @MethodSource("task2Data")
    @DisplayName("task2")
    public void testTask2(String input, boolean expected) {
        // When
        boolean result = Task8.task2(input);

        // Then
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @MethodSource("task3Data")
    @DisplayName("task3")
    public void testTask3(String input, boolean expected) {
        // When
        boolean result = Task8.task3(input);

        // Then
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @MethodSource("task4Data")
    @DisplayName("task4")
    public void testTask4(String input, boolean expected) {
        // When
        boolean result = Task8.task4(input);

        // Then
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @MethodSource("task5Data")
    @DisplayName("task5")
    public void testTask5(String input, boolean expected) {
        // When
        boolean result = Task8.task5(input);

        // Then
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @MethodSource("task6Data")
    @DisplayName("task6")
    public void testTask6(String input, boolean expected) {
        // When
        boolean result = Task8.task6(input);

        // Then
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @MethodSource("task7Data")
    @DisplayName("task7")
    public void testTask7(String input, boolean expected) {
        // When
        boolean result = Task8.task7(input);

        // Then
        assertEquals(expected, result);
    }
}
