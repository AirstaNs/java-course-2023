package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class Task4Test {

    @ParameterizedTest
    @DisplayName("Должно возвращать true, если пароль содержит требуемый символ")
    @ValueSource(strings = {"password~", "!secure", "test@", "#1234", "$test", "%percent", "^power", "&ampersand", "*add", "|pip"})
    public void testIsValidPassword_Valid(String password) {
        // When
        boolean result = Task4.isValidatePassword(password);

        // Then
        assertTrue(result);
    }

    @ParameterizedTest
    @DisplayName("Должно возвращать false, если пароль не содержит требуемый символ")
    @ValueSource(strings = {"nopassword", "pass", "simple1234"})
    public void testIsValidPassword_Invalid(String password) {
        // When
        boolean result = Task4.isValidatePassword(password);

        // Then
        assertFalse(result);
    }
}
