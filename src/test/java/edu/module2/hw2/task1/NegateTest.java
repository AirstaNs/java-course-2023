package edu.module2.hw2.task1;

import edu.module2.hw2.task1.Constant;
import edu.module2.hw2.task1.Expr;
import edu.module2.hw2.task1.Negate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class NegateTest {

    @Test
    @DisplayName("Отрицание: При значении NaN выбрасывается ArithmeticException")
    public void whenNaNValue_thenThrowsArithmeticException() {
        assertThrows(ArithmeticException.class, () -> new Negate(new Constant(Double.NaN)));
    }

    @Test
    @DisplayName("Отрицание: При максимальном значении Double")
    public void whenMaxDoubleValue_thenCorrectlyNegated() {
        // Given
        Expr maxNegate = new Negate(new Constant(Double.MAX_VALUE));

        // When
        double result = maxNegate.evaluate();

        // Then
        assertEquals(-Double.MAX_VALUE, result);
    }

    @Test
    @DisplayName("Отрицание: При минимальном значении Double")
    public void whenMinDoubleValue_thenCorrectlyNegated() {
        // Given
        Expr minNegate = new Negate(new Constant(Double.MIN_VALUE));

        // When
        double result = minNegate.evaluate();

        // Then
        assertEquals(-Double.MIN_VALUE, result);
    }

    @Test
    @DisplayName("Отрицание: При обычном значении оно корректно возвращается с обратным знаком")
    public void testNormalValue() {
        // Given
        Expr five = new Constant(5);
        Expr minusFive = new Negate(five);

        // When
        double result = minusFive.evaluate();

        // Then
        assertEquals(-5, result);
    }
}
