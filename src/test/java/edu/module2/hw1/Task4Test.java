package edu.module2.hw1;

import edu.module2.hw1.Task4;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task4Test {
    @Test
    @DisplayName("Исправление строк с четной длиной")
    public void testFixStringForEvenLengthInputs() {
        String[] inputs = new String[]{
            "123456",
            "hTsii  s aimex dpus rtni.g",
            "ab"
        };

        String[] expectedOutputs = new String[]{
            "214365",
            "This is a mixed up string.",
            "ba"
        };

        for (int i = 0; i < inputs.length; i++) {
            assertEquals(expectedOutputs[i], Task4.fixString(inputs[i]));
        }
    }

    @Test
    @DisplayName("Исправление строк с нечетной длиной")
    public void testFixStringForOddLengthInputs() {
        String[] inputs = new String[]{
            "badce",
            "a"
        };

        String[] expectedOutputs = new String[]{
            "abcde",
            "a"
        };

        for (int i = 0; i < inputs.length; i++) {
            assertEquals(expectedOutputs[i], Task4.fixString(inputs[i]));
        }
    }

    @Test
    @DisplayName("Обработка null-значений и пустой строки")
    public void testFixStringForSpecialInputs() {
        String empty = "";
        assertThrows(NullPointerException.class, () -> Task4.fixString(null));
        assertEquals(empty, Task4.fixString(empty));
    }
}
