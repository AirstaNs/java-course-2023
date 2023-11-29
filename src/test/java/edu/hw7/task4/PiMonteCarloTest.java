package edu.hw7.task4;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.time.Duration;
import java.time.Instant;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

class PiMonteCarloTest {

    private static final int CORES = Runtime.getRuntime().availableProcessors();

    @Test
    void testPiApproximationAccuracy() {
        int totalIterations = 1000000;

        double pi = PiMonteCarlo.calculatePi(totalIterations, CORES);

        assertTrue(Math.abs(pi - Math.PI) < 0.01);
    }

    @Test
    void testWithDifferentThreadCounts() {
        int totalIterations = 500000;

        double piWithTwoThreads = PiMonteCarlo.calculatePi(totalIterations, CORES);
        double piWithEightThreads = PiMonteCarlo.calculatePi(totalIterations, CORES);

        assertTrue(Math.abs(piWithTwoThreads - Math.PI) < 0.01);
        assertTrue(Math.abs(piWithEightThreads - Math.PI) < 0.01);
    }

    private static Stream<Arguments> provideTestParameters() {
        return Stream.of(
                // Комбинации для 10 миллионов итераций
                Arguments.of(10_000_000, 1),
                Arguments.of(10_000_000, 6),
                Arguments.of(10_000_000,  Runtime.getRuntime().availableProcessors()),
                // Комбинации для 100 миллионов итераций
                Arguments.of(100_000_000, 1),
                Arguments.of(100_000_000, 6),
                Arguments.of(100_000_000, Runtime.getRuntime().availableProcessors()),
                // Комбинации для 1 миллиарда итераций
                Arguments.of(1_000_000_000, 1),
                Arguments.of(1_000_000_000, 6),
                Arguments.of(1_000_000_000, Runtime.getRuntime().availableProcessors())
            );
    }


    @ParameterizedTest
    @MethodSource("provideTestParameters")
    public void testPiCalculationPerformance(int totalIterations, int threadCount) {
        Instant startTime = Instant.now();
        double pi = PiMonteCarlo.calculatePi(totalIterations, threadCount);
        Instant endTime = Instant.now();

        Duration duration = Duration.between(startTime, endTime);
        double seconds = duration.getSeconds() + duration.getNano() / 1e9;
        double error = Math.abs(pi - Math.PI);

        System.out.printf("Threads: %d, Iterations: %d, Time: %.2f s, Pi: %f, Error: %.9f%n",
            threadCount, totalIterations, seconds, pi, error);

        assertTrue(error < 0.01);
    }
}
