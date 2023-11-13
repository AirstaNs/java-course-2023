package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class Task5Test {

    @ParameterizedTest
    @DisplayName("Должно возвращать true для правильных номерных знаков")
    @ValueSource(strings = {"А123ВЕ777", "О777ОО177"})
    public void testIsValidNumber_Valid(String number) {
        // When
        boolean result = Task5.isValidateNumber(number);

        // Then
        assertTrue(result);
    }

    @ParameterizedTest
    @DisplayName("Должно возвращать false для неправильных номерных знаков")
    @ValueSource(strings = {"123АВЕ777", "А123ВГ77", "А123ВЕ7777"})
    public void testIsValidNumber_Invalid(String number) {
        // When
        boolean result = Task5.isValidateNumber(number);

        // Then
        assertFalse(result);
    }
}
