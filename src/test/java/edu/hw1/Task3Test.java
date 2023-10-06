package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task3Test {
    @Test
    @DisplayName("Проверка вложенности различных массивов")
    public void testArrayTestabilityForVariousArrays() {
        int[][] a1Arrays = {
            {1, 2, 3, 4},
            {3, 1},
            {9, 9, 8},
            {1, 2, 3, 4}
        };

        int[][] a2Arrays = {
            {0, 6},
            {4, 0},
            {8, 9},
            {2, 3}
        };

        boolean[] expectedOutputs = {
            true,
            true,
            false,
            false
        };

        for (int i = 0; i < a1Arrays.length; i++) {
            assertEquals(expectedOutputs[i], Task3.isNestable(a1Arrays[i], a2Arrays[i]));
        }
    }

    @Test
    @DisplayName("Проверка вложенности для пограничных случаев")
    public void testArrayTestabilityForBoundaryCases() {
        int[][] a1Arrays = {
            {1, 2},
            {3, 1},
            {9, 9},
            {0}
        };

        int[][] a2Arrays = {
            {0, 2},
            {1, 4},
            {9, 9},
            {0, 0}
        };

        for (int i = 0; i < a1Arrays.length; i++) {
            assertFalse(Task3.isNestable(a1Arrays[i], a2Arrays[i]));
        }
    }

    @Test
    @DisplayName("Обработка null и пустых массивов")
    public void testNullAndEmptyArrayHandling() {
        int[] nonEmptyArray = new int[]{1, 2};
        int[] emptyArray = new int[]{};

        assertThrows(NullPointerException.class, () -> Task3.isNestable(null, nonEmptyArray));
        assertThrows(NullPointerException.class, () -> Task3.isNestable(nonEmptyArray, null));
        assertThrows(NullPointerException.class, () -> Task3.isNestable(null, null));

        assertFalse(Task3.isNestable(emptyArray, nonEmptyArray));
        assertFalse(Task3.isNestable(nonEmptyArray, emptyArray));
        assertFalse(Task3.isNestable(emptyArray, emptyArray));
    }
}
