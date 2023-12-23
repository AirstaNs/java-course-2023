package edu.module4.hw5;

import edu.module4.hw5.Task5;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class Task5Test {

    @ParameterizedTest
    @DisplayName("Должно возвращать true для правильных номерных знаков")
    @ValueSource(strings = {"А123ВЕ777", "О777ОО177","А123ВЕ12"})
    public void testIsValidNumber_Valid(String number) {
        // When
        boolean result = Task5.isValidNumber(number);

        // Then
        assertTrue(result);
    }

    @ParameterizedTest
    @DisplayName("Должно возвращать false для неправильных номерных знаков")
    @ValueSource(strings = {"123АВЕ777", "А123ВГ77", "А123ВЕ7777","А000ВЕ777","А123ВЕ00"})
    public void testIsValidNumber_Invalid(String number) {
        // When
        boolean result = Task5.isValidNumber(number);

        // Then
        assertFalse(result);
    }
}
