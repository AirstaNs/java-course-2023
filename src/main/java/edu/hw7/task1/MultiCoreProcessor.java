package edu.hw7.task1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class MultiCoreProcessor {

    private static final int CORES = Runtime.getRuntime().availableProcessors();
    private final int totalOperations;
    private final AtomicInteger completedOperations = new AtomicInteger(0);

    public MultiCoreProcessor(int totalOperations) {
        this.totalOperations = totalOperations;
    }

    public void executeOperations() {
        ExecutorService executor = Executors.newFixedThreadPool(CORES);

        for (int i = 0; i < totalOperations; i++) {
            executor.submit(this::performOperation);
        }
        executor.close();
    }

    private void performOperation() {
        completedOperations.incrementAndGet();
    }

    public int getCompletedOperations() {
        return completedOperations.get();
    }
}
