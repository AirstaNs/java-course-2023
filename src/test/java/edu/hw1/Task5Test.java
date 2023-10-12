package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task5Test {
    @Test
    @DisplayName("Числа, которые являются палиндромами или имеют потомков-палиндромы")
    public void testNumbersWithPalindromeDescendants() {
        long[] inputs = new long[]{
            11211230,
            13001120,
            23336014,
            123312,
            1221,
            121,
            11
        };

        for (long input : inputs) {
            assertTrue(Task5.isPalindromeDescendant(input));
        }
    }

    @Test
    @DisplayName("Числа, которые не являются палиндромами и не имеют потомков-палиндромы")
    public void testNumbersWithoutPalindromeDescendants() {
        long[] inputs = new long[] {
            1234567890123456L,
            11211231,
            1234567,
            987654,
            123456,
            12345,
            8,
            5,
            0
        };

        for (long input : inputs) {
            assertFalse(Task5.isPalindromeDescendant(input));
        }
    }

    @Test
    @DisplayName("Проверка отрицательных чисел")
    public void testNegativeNumbers() {
        int[] negativeInputs = new int[]{
            -23336014,
            -13001121,
            -11211230,
            -11
        };

        boolean[] expectedOutputsForNegatives = new boolean[]{
            true,
            false,
            true,
            true
        };

        for (int i = 0; i < negativeInputs.length; i++) {
            assertEquals(expectedOutputsForNegatives[i], Task5.isPalindromeDescendant(negativeInputs[i]));
        }
    }
}
