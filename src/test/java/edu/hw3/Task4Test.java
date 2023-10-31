package edu.hw3;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task4Test {
    private static List<Arguments> stringProvider() {
        return List.of(
            Arguments.of(2, "II"),
            Arguments.of(12, "XII"),
            Arguments.of(16, "XVI"),
            Arguments.of(777, "DCCLXXVII"),
            Arguments.of(991, "CMXCI")
            );

    }

    @ParameterizedTest
    @MethodSource("stringProvider")
    @DisplayName("Arabic Number to Roman Conversion")
    void testValidateArabicToRomanConversion(int arabicValue, String expected) {
        // When
        String result = Task4.convertToRoman(arabicValue);
        // Then
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Handling special input data in convertToRoman")
    public void testHandleSpecialInputsForConvertToRoman() {
        // When & Then
        assertThrows(IllegalArgumentException.class, () -> Task4.convertToRoman(0));
        assertThrows(IllegalArgumentException.class, () -> Task4.convertToRoman(-1));
        assertThrows(IllegalArgumentException.class, () -> Task4.convertToRoman(4000));
    }
}
