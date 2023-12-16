package edu.hw10.task2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FibCalculatorTest {
    private FibCalculator fibCalculator;

    @BeforeEach
    public void setUp() {
        fibCalculator = new FibCalculatorImpl();
        fibCalculator = CacheProxy.create(fibCalculator, FibCalculator.class);
    }

    @Test
    public void testFibonacci() {
        long fib5 = fibCalculator.fib(5);
        assertEquals(5, fib5);

        long fib10 = fibCalculator.fib(10);
        assertEquals(55, fib10);

        long fib15 = fibCalculator.fib(15);
        assertEquals(610, fib15);
    }
}

