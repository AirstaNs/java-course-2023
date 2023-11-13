package edu.hw5;

import edu.hw5.task1.Task1;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task1Test {

    @Test
    @DisplayName("Должно корректно вычислять среднее время посещения")
    public void testGetFormattedAverageIntervals() {
        // Given
        List<String> intervals = Arrays.asList(
            "2022-03-12, 20:20 - 2022-03-12, 23:50",
            "2022-04-01, 21:30 - 2022-04-02, 01:20"
        );

        // When
        String result = Task1.getFormattedAverageIntervals(intervals);

        // Then
        assertEquals("3ч 40м", result);
    }

    @Test
    @DisplayName("Должно выбросить исключение при неправильной строке")
    public void testInvalidIntervalString() {
        // Given
        List<String> intervals = Arrays.asList(
            "2022-03-12, 20:20 - 2022-03-12, 23:50",
            "InvalidInterval"
        );

        // When, Then
        assertThrows(DateTimeParseException.class, () -> Task1.getFormattedAverageIntervals(intervals));
    }

    @Test
    @DisplayName("Должно выбросить исключение при null строке")
    public void testNullIntervalString() {
        // When, Then
        assertThrows(NullPointerException.class, () -> Task1.getFormattedAverageIntervals(null));
    }

    @Test
    @DisplayName("Должно выбросить исключение при интервале с неправильным порядком start и end")
    public void testInvalidIntervalOrder() {
        // Given
        List<String> intervals = List.of("2022-03-15, 10:00 - 2022-03-14, 15:30");

        // When, Then
        assertThrows(IllegalArgumentException.class, () -> Task1.getFormattedAverageIntervals(intervals));
    }
}
