package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task2Test {

    @Test
    @DisplayName("Количество цифр в различных числах")
    public void testCountDigitsForVariousNumbers() {
        int[] inputs = new int[]{
            4666,
            544,
            0,
            1,
            1234567890,
            999999999
        };

        int[] expectedOutputs = new int[]{
            4,
            3,
            1,
            1,
            10,
            9
        };

        for (int i = 0; i < inputs.length; i++) {
            assertEquals(expectedOutputs[i], Task2.countDigits(inputs[i]));
        }
    }

    @Test
    @DisplayName("Отрицательные числа обрабатываются так же, как положительные")
    public void testCountDigitsForNegativeNumbers() {
        int[] negativeInputs = new int[]{
            -4666,
            -544,
            -1,
            -1234567890,
            -999999999
        };

        int[] expectedOutputsForNegatives = new int[]{
            4,
            3,
            1,
            10,
            9
        };

        for (int i = 0; i < negativeInputs.length; i++) {
            assertEquals(expectedOutputsForNegatives[i], Task2.countDigits(negativeInputs[i]));
        }
    }
}
