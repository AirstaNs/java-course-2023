package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task2Test {

    @Test
    @DisplayName("Должно корректно находить все пятницы 13-е в заданном году")
    public void testFindFridays13() {
        // Given
        int year = 2024;

        // When
        List<LocalDate> result = Task2.findFridays13(year);

        // Then
        assertEquals(
            List.of(LocalDate.of(year, 9, 13),
            LocalDate.of(year, 12, 13)), result);
    }

    @Test
    @DisplayName("Должно корректно находить следующую ближайшую пятницу 13")
    public void testFindNextFriday13() {
        // Given
        LocalDate currentDate = LocalDate.of(2023, 5, 1);

        // When
        LocalDate result = Task2.findNextFriday13(currentDate);

        // Then
        assertEquals(LocalDate.of(2023, 5, 13), result);
    }
}
