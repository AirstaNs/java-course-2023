package edu.hw2.task1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AdditionTest {

    @Test
    @DisplayName("Сложение: При сумме 3 и 5 результат равен 8")
    public void whenAdding3And5_thenResultIs8() {
        // Given
        Expr addition = new Addition(new Constant(3), new Constant(5));

        // When
        double result = addition.evaluate();

        // Then
        assertEquals(8, result);
    }

    @Test
    @DisplayName("Сложение: При сумме отрицательных чисел")
    public void whenAddingNegativeNumbers_thenCorrectlySums() {
        // Given
        Expr addition = new Addition(new Constant(-3), new Constant(-5));

        // When
        double result = addition.evaluate();

        // Then
        assertEquals(-8, result);
    }

    @Test
    @DisplayName("Сложение: При сумме нулей результат равен 0")
    public void whenAddingZeros_thenResultIs0() {
        // Given
        Expr addition = new Addition(new Constant(0), new Constant(0));

        // When
        double result = addition.evaluate();

        // Then
        assertEquals(0, result);
    }

    @Test
    @DisplayName("Сложение: При сумме двух максимальных значений Double выбрасывается ArithmeticException")
    public void whenAddingMaxDoubleValues_thenThrowsArithmeticException() {
        // Given
        Expr addition = new Addition(new Constant(Double.MAX_VALUE), new Constant(Double.MAX_VALUE));

        //Then
        assertThrows(ArithmeticException.class, addition::evaluate);
    }

    @Test
    @DisplayName("Сложение: При сумме двух минимальных значений Double")
    public void whenAddingMinDoubleValues_thenCorrectlySums() {
        // Given
        Expr addition = new Addition(new Constant(Double.MIN_VALUE), new Constant(Double.MIN_VALUE));

        // When
        double result = addition.evaluate();

        // Then
        assertEquals(Double.MIN_VALUE + Double.MIN_VALUE, result);
    }
}
