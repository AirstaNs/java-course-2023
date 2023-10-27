package edu.hw2.task1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MultiplicationTest {

    @Test
    @DisplayName("Умножение: При умножении 3 на 5 результат равен 15")
    public void whenMultiplying3By5_thenResultIs15() {
        // Given
        Expr multiplication = new Multiplication(new Constant(3), new Constant(5));

        // When
        double result = multiplication.evaluate();

        // Then
        assertEquals(15, result);
    }

    @Test
    @DisplayName("Умножение: Умножение на 0 даёт 0")
    public void whenMultiplyingByZero_thenResultIsZero() {
        // Given
        Expr multiplication = new Multiplication(new Constant(42), new Constant(0));

        // When
        double result = multiplication.evaluate();

        // Then
        assertEquals(0, result);
    }

    @Test
    @DisplayName("Умножение: Умножение двух отрицательных чисел даёт положительный результат")
    public void whenMultiplyingTwoNegativeNumbers_thenResultIsPositive() {
        // Given
        Expr multiplication = new Multiplication(new Constant(-3), new Constant(-5));

        // When
        double result = multiplication.evaluate();

        // Then
        assertEquals(15, result);
    }

    @Test
    @DisplayName("Умножение: При умножении двух максимальных значений Double выбрасывается ArithmeticException")
    public void whenMultiplyingMaxDoubleValues_thenThrowsArithmeticException() {
        // Given
        Expr multiplication = new Multiplication(new Constant(Double.MAX_VALUE), new Constant(Double.MAX_VALUE));

        //Then
        assertThrows(ArithmeticException.class, multiplication::evaluate);
    }

    @Test
    @DisplayName("Умножение: При умножении двух минимальных значений Double")
    public void whenMultiplyingMinDoubleValues_thenCorrectlyMultiplies() {
        // Given
        Expr multiplication = new Multiplication(new Constant(Double.MIN_VALUE), new Constant(Double.MIN_VALUE));

        // When
        double result = multiplication.evaluate();

        // Then
        assertEquals(Double.MIN_VALUE * Double.MIN_VALUE, result);
    }
}
