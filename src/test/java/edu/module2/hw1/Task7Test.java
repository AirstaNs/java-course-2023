package edu.module2.hw1;

import edu.module2.hw1.Task1;
import edu.module2.hw1.Task7;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task7Test {

    @Test
    @DisplayName("Базовые тесты для циклического сдвига")
    public void testBasicRotateCases() {
        int[] numbers = new int[]{
            8,
            16,
            17
        };

        int[] shifts = new int[]{
            1,
            1,
            2
        };

        int[] expectedOutputsForRight = new int[]{
            4,
            8,
            12
        };

        int[] expectedOutputsForLeft = new int[]{
            1,
            1,
            6
        };

        for (int i = 0; i < numbers.length; i++) {
            assertEquals(expectedOutputsForRight[i], Task7.rotateRight(numbers[i], shifts[i]));
            assertEquals(expectedOutputsForLeft[i], Task7.rotateLeft(numbers[i], shifts[i]));
        }
    }

    @Test
    @DisplayName("Тестирование невалидных входных данных")
    public void testInvalidInputs() {
        int[] numbers = new int[]{
            -8,
            16,
            0
        };

        int[] shifts = new int[]{
            1,
            -1,
            0
        };

        for (int i = 0; i < numbers.length; i++) {
            assertEquals(Task1.INVALID_NUMBER, Task7.rotateRight(numbers[i], shifts[i]));
            assertEquals(Task1.INVALID_NUMBER, Task7.rotateLeft(numbers[i], shifts[i]));
        }
    }

    @Test
    @DisplayName("Тестирование крупных сдвигов")
    public void testLargeShifts() {
        int[] numbers = new int[]{
            8,
            16
        };

        int[] shifts = new int[]{
            5,
            6
        };

        int[] expectedOutputsForRight = new int[]{
            4,
            8
        };

        int[] expectedOutputsForLeft = new int[]{
            1,
            1
        };

        for (int i = 0; i < numbers.length; i++) {
            assertEquals(expectedOutputsForRight[i], Task7.rotateRight(numbers[i], shifts[i]));
            assertEquals(expectedOutputsForLeft[i], Task7.rotateLeft(numbers[i], shifts[i]));
        }
    }
}
