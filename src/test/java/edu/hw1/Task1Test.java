package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task1Test {

    @Test
    @DisplayName("Корректное преобразование времени")
    public void testCorrectDurations() {
        String[] correctInputs = new String[]{
            "00:00",
            "13:56",
            "13:58",
            "23:09",
            "698:00",
            "698:01",
            "698:59"
        };
        long[] correctOutputs = new long[]{
            0,
            836,
            838,
            1389,
            41880,
            41881,
            41939
        };

        for (int i = 0; i < correctInputs.length; i++) {
            assertEquals(correctOutputs[i], Task1.minutesToSeconds(correctInputs[i]));
        }
    }

    @Test
    @DisplayName("Неверный формат времени")
    public void testIncorrectDurations() {
        String[] incorrectInputs = new String[]{
            "698:60",
            "60:698",
            "asd:12",
            "12:as",
            "12:1a",
            "a2:12",
            "a!:f2"
        };

        for (String input : incorrectInputs) {
            assertEquals(Task1.INVALID_NUMBER, Task1.minutesToSeconds(input));
        }
    }
    @Test
    @DisplayName("Некорректная строка")
    public void incorrectDelimiter() {
        String[] durationsWithIncorrectDelimiters = {
            "1000",
            "!!",
            "##",
            "@@",
            "::::",
            "!!:"
        };

        for (String duration : durationsWithIncorrectDelimiters) {
            long seconds = Task1.minutesToSeconds(duration);
            assertThat(seconds).isEqualTo(Task1.INVALID_NUMBER);
        }
    }
    @Test
    @DisplayName("Некорректная строка, только из разделителей")
    public void onlyDelimiters() {
        String[] durationsOnlyDelimiters = {
            ":",
            "::",
            ":::",
            "::::",
            ":::::"
        };

        for (String duration : durationsOnlyDelimiters) {
            long seconds = Task1.minutesToSeconds(duration);
            assertThat(seconds).isEqualTo(Task1.INVALID_NUMBER);
        }
    }
    @Test
    @DisplayName("Обработка null-значений")
    public void testFixStringForSpecialInputs() {
        assertThrows(NullPointerException.class, () -> Task1.minutesToSeconds(null));
    }
}
