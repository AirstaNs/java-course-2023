package edu.module2.hw1;

import edu.module2.hw1.Task1;
import edu.module2.hw1.Task6;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task6Test {

    @Test
    @DisplayName("Числа, которые становятся 6174 за определенное количество шагов")
    public void testCountKForVariousNumbers() {
        int[] inputs = new int[]{
            3524,
            6621,
            6554,
            1234,
            2017,
            9876,
            3412,
            4321,
            2001
        };

        int[] expectedOutputs = new int[]{
            3,
            5,
            4,
            3,
            3,
            3,
            3,
            3,
            3
        };

        for (int i = 0; i < inputs.length; i++) {
            assertEquals(expectedOutputs[i], Task6.countK(inputs[i]));
        }
    }
    @Test
    @DisplayName("Тест числа Капрекара")
    public void testKaprekarNumber() {
        assertEquals(0, Task6.countK(Task6.KAPREKAR_CONSTANT));
    }
    @Test
    @DisplayName("Тестирование неправильных значений")
    public void testBorderAndInvalidValues() {
        int[] inputs = new int[]{
            1000000,
            9999,
            8888,
            5555,
            4444,
            3333,
            2222,
            1111,
            1000,
            999,
            -3524
        };

        for (int input : inputs) {
            int i = Task6.countK(input);
            assertEquals(Task1.INVALID_NUMBER, i);
        }
    }
}
