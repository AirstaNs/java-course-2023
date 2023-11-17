package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task2Test {

    @Test
    @DisplayName("Должно корректно находить все пятницы 13-е в заданном году")
    public void testFindFridays13() {
        // Given
        int year = 2024;
        int startMonth = 1;
        int endMonth = 12;

        // When
        List<LocalDate> result = Task2.findFridays13(year, startMonth, endMonth);
        List<LocalDate> expected = List.of(LocalDate.of(year, 9, 13), LocalDate.of(year, 12, 13));
        // Then
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Должно корректно находить следующую ближайшую пятницу 13")
    public void testFindNextFriday13() {
        // Given
        LocalDate currentDate = LocalDate.of(2022, 5, 1);

        // When
        LocalDate result = Task2.findNextFriday13(currentDate);

        // Then
        assertEquals(LocalDate.of(2023, 1, 13), result);
    }

    @Test
    @DisplayName("Должно корректно находить следующую ближайшую пятницу 13")
    public void testNotFindNextFriday13() {
        // Given
        LocalDate currentDate = LocalDate.of(2023, 5, 1);

        // When
        LocalDate result = Task2.findNextFriday13(currentDate);

        // Then
        assertNotEquals(LocalDate.of(2023, 5, 13), result);
    }

    @Test
    @DisplayName("Должно бросать исключение для недопустимого года")
    public void testInvalidYear() {
        // Given
        int invalidYear = -2023;

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> Task2.findFridays13(invalidYear, 1, 12));
    }

    @Test
    @DisplayName("Должно бросать исключение для недопустимой даты")
    public void testInvalidDate() {
        assertThrows(NullPointerException.class, () -> Task2.findNextFriday13(null));
    }
}
