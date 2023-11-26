package edu.hw7.task2;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.List;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

class FactorialCalculatorTest {
    private static List<Arguments> factorialData() {
        return List.of(
            Arguments.of(0, 1),
            Arguments.of(1, 1),
            Arguments.of(2, 2),
            Arguments.of(3, 6),
            Arguments.of(4, 24),
            Arguments.of(5, 120),
            Arguments.of(15, 1307674368000L)
        );
    }
    private static Stream<Integer> negativeData() {
        return Stream.of(-1, -2, -10);
    }

    @ParameterizedTest
    @MethodSource("factorialData")
    void testFactorial(int input, long expected) {
        assertEquals(expected, FactorialCalculator.factorial(input));
    }

    @ParameterizedTest
    @MethodSource("negativeData")
    void testFactorialWithNegativeInput(int input) {
        assertThrows(IllegalArgumentException.class, () -> FactorialCalculator.factorial(input));
    }
}
