package edu.hw7.task4;

import org.junit.jupiter.api.Test;
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
}
