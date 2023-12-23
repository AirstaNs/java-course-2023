package edu.module2.hw2.task1;

import edu.module2.hw2.task1.Constant;
import edu.module2.hw2.task1.Expr;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ConstantTest {

    @Test
    @DisplayName("Константа: При максимальном значении Double")
    public void whenMaxDoubleValue_thenCorrectlyReturns() {
        // Given
        Expr maxDouble = new Constant(Double.MAX_VALUE);

        // When
        double result = maxDouble.evaluate();

        // Then
        assertEquals(Double.MAX_VALUE, result);
    }

    @Test
    @DisplayName("Константа: При минимальном значении Double")
    public void whenMinDoubleValue_thenCorrectlyReturns() {
        // Given
        Expr minDouble = new Constant(Double.MIN_VALUE);

        // When
        double result = minDouble.evaluate();

        // Then
        assertEquals(Double.MIN_VALUE, result);
    }

    @Test
    @DisplayName("Константа: При значении NaN выбрасывается ArithmeticException")
    public void whenNaNValue_thenThrowsArithmeticException() {
        assertThrows(ArithmeticException.class, () -> new Constant(Double.NaN));
    }

    @Test
    @DisplayName("Константа: При бесконечном значении выбрасывается ArithmeticException")
    public void whenInfinityValue_thenThrowsArithmeticException() {
        assertThrows(ArithmeticException.class, () -> new Constant(Double.POSITIVE_INFINITY));
    }

    @Test
    @DisplayName("Константа: Создание константы со значением Double.MAX_VALUE не вызывает исключение")
    public void whenCreatingConstantWithMaxValue_thenItIsCreatedWithoutException() {
        assertDoesNotThrow(() -> new Constant(Double.MAX_VALUE));
    }

    @Test
    @DisplayName("Константа: Создание константы со значением Double.MIN_VALUE не вызывает исключение")
    public void whenCreatingConstantWithMinValue_thenItIsCreatedWithoutException() {
        assertDoesNotThrow(() -> new Constant(Double.MIN_VALUE));
    }

    @Test
    @DisplayName("Конструктор Constant вызывает метод checkForOverflow")
    public void constructorCallsCheckForOverflow() {
        assertThrows(ArithmeticException.class, () -> new Constant(Double.NaN));
    }

    @Test
    @DisplayName("Метод evaluate корректно возвращает значение")
    public void evaluateReturnsCorrectValue() {
        // Given
        double testValue = 5.0;
        Constant constant = new Constant(testValue);

        // When & Then
        assertEquals(testValue, constant.evaluate());
        assertEquals(testValue, constant.value());
    }
}
