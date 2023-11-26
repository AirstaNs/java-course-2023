package edu.hw7.task4;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public final class PiMonteCarlo {

    private static final int TIMEOUT = 20;
    private static final double FOUR = 4.0;

    private PiMonteCarlo() {
    }

    public static double calculatePi(int totalIterations, int threadCount) {
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        AtomicLong circleCount = new AtomicLong(0);
        AtomicLong totalCount = new AtomicLong(0);

        for (int i = 0; i < threadCount; i++) {
            final int iterationsPerThread = totalIterations / threadCount;

            executor.submit(() -> {
                long localCircleCount = 0;
                for (int j = 0; j < iterationsPerThread; j++) {
                    double x = ThreadLocalRandom.current().nextDouble();
                    double y = ThreadLocalRandom.current().nextDouble();

                    if (x * x + y * y <= 1) {
                        localCircleCount++;
                    }
                }
                circleCount.addAndGet(localCircleCount);
                totalCount.addAndGet(iterationsPerThread);
            });
        }

        executor.shutdown();
        try {
            executor.awaitTermination(TIMEOUT, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return FOUR * circleCount.get() / totalCount.get();
    }
}
