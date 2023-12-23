package edu.module5.hw7.task4;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicLong;

public final class PiMonteCarlo {

    private static final double FOUR = 4.0;

    private PiMonteCarlo() {
    }

    public static double calculatePi(int totalIterations, int threadCount) {
        if (threadCount <= 1) {
            return calculatePiSingleThreaded(totalIterations);
        }
        AtomicLong circleCount = new AtomicLong(0);
        AtomicLong totalCount = new AtomicLong(0);
        try (ExecutorService executor = Executors.newFixedThreadPool(threadCount)) {
            int iterationsPerThread = totalIterations / threadCount;
            int remainder = totalIterations % threadCount;

            for (int i = 0; i < threadCount; i++) {
                final int finalCount = iterationsPerThread + (i < remainder ? 1 : 0);
                executor.submit(() -> {
                    long points = calculatePointsInCircle(finalCount);
                    circleCount.addAndGet(points);
                    totalCount.addAndGet(finalCount);
                });
            }
        }
        return FOUR * circleCount.get() / totalCount.get();
    }

    private static double calculatePiSingleThreaded(int totalIterations) {
        long circleCount = calculatePointsInCircle(totalIterations);
        return FOUR * circleCount / totalIterations;
    }

    private static long calculatePointsInCircle(int iterations) {
        long circleCount = 0;
        for (int i = 0; i < iterations; i++) {
            double x = ThreadLocalRandom.current().nextDouble();
            double y = ThreadLocalRandom.current().nextDouble();

            if (x * x + y * y <= 1) {
                circleCount++;
            }
        }
        return circleCount;
    }
}
