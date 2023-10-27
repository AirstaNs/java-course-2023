package edu.hw2.task1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ExponentTest {

    @Test
    @DisplayName("Экспонента: 2 в степени 3 равно 8")
    public void whenExponent2RaisedTo3_thenResultIs8() {
        // Given
        Expr exponent = new Exponent(new Constant(2), 3);

        // When
        double result = exponent.evaluate();

        // Then
        assertEquals(8, result);
    }

    @Test
    @DisplayName("Экспонента: Любое число в степени 0 равно 1")
    public void whenExponentAnyNumberRaisedTo0_thenResultIs1() {
        // Given
        Expr exponent = new Exponent(new Constant(42), 0);

        // When
        double result = exponent.evaluate();

        // Then
        assertEquals(1, result);
    }

    @Test
    @DisplayName("Экспонента: 0 в любой степени равно 0")
    public void whenExponent0RaisedToAnyNumber_thenResultIs0() {
        // Given
        Expr exponent = new Exponent(new Constant(0), 5);

        // When
        double result = exponent.evaluate();

        // Then
        assertEquals(0, result);
    }

    @Test
    @DisplayName("Экспонента: max значение в max степень выбрасывается ArithmeticException")
    public void whenExponentMaxDoubleValues_thenThrowsArithmeticException() {
        // Given
        Expr exponent = new Exponent(new Constant(Double.MAX_VALUE), Double.MAX_VALUE);

        //Then
        assertThrows(ArithmeticException.class, exponent::evaluate);
    }

    @Test
    @DisplayName("Экспонента: При возведении min значения Double в min степень")
    public void whenExponentMinDoubleValues_thenCorrectlyExponents() {
        // Given
        Expr exponent = new Exponent(new Constant(Double.MIN_VALUE), Double.MIN_VALUE);

        // When
        double result = exponent.evaluate();

        // Then
        assertEquals(Math.pow(Double.MIN_VALUE, Double.MIN_VALUE), result);
    }
}
