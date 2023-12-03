package edu.hw8;

import edu.hw8.task2.FixedThreadPool;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FixedThreadPoolTest {
    private static final int CORE = Runtime.getRuntime().availableProcessors();

    @ParameterizedTest
    @MethodSource("fibonacciDataProvider")
    public void testFibonacciCalculation(int fibIndex, int expectedResult) {
        try (FixedThreadPool threadPool = new FixedThreadPool(CORE)) {

            CompletableFuture<Integer> futureResult = CompletableFuture.supplyAsync(() -> fibonacci(fibIndex),
                threadPool
            );

            assertEquals(expectedResult, futureResult.join().intValue());
        }
    }

    private static Stream<Arguments> fibonacciDataProvider() {
        return Stream.of(Arguments.of(0, 0),
            Arguments.of(1, 1),
            Arguments.of(2, 1),
            Arguments.of(3, 2),
            Arguments.of(4, 3),
            Arguments.of(5, 5),
            Arguments.of(6, 8),
            Arguments.of(7, 13),
            Arguments.of(8, 21),
            Arguments.of(9, 34),
            Arguments.of(10, 55),
            Arguments.of(11, 89),
            Arguments.of(12, 144),
            Arguments.of(13, 233),
            Arguments.of(14, 377),
            Arguments.of(15, 610),
            Arguments.of(35, 9227465)
        );
    }

    public static int fibonacci(final int elementNumber) {
        if (elementNumber == 1 || elementNumber == 0) {
            return elementNumber;
        }
        return fibonacci(elementNumber - 1) + fibonacci(elementNumber - 2);
    }
}
