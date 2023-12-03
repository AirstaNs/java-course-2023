package edu.hw8.task3;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class PasswordCracker {
    private final AtomicReference<String> foundPassword = new AtomicReference<>(null);
    private final AtomicBoolean found = new AtomicBoolean(false);

    private final PasswordGenerator passwordGenerator;

    public PasswordCracker(PasswordGenerator generator) {
        this.passwordGenerator = generator;
    }

    public String crackPassword(String targetHash, int numberOfThreads) {
        try (ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads)) {
            CompletableFuture<?>[] futures = new CompletableFuture[numberOfThreads];
            long totalCombinations = passwordGenerator.getTotalCombinations();
            long chunkSize = totalCombinations / numberOfThreads;

            for (int i = 0; i < numberOfThreads; i++) {
                final long start = i * chunkSize;
                final long end = (i == numberOfThreads - 1) ? totalCombinations : start + chunkSize;
                futures[i] = CompletableFuture.supplyAsync(() -> passwordSearch(targetHash, start, end), executor)
                                              .thenAccept(result -> {
                                                  if (result != null) {
                                                      executor.shutdownNow();
                                                  }
                                              });
            }

            CompletableFuture.allOf(futures).join();
            return foundPassword.get();
        }
    }

    private String passwordSearch(String targetHash, long start, long end) {
        for (long index = start; index < end && !found.get(); index++) {
            String password = passwordGenerator.convertToIndexBasedPassword(index);
            if (password != null && HashUtils.hashPassword(password).equals(targetHash)) {
                found.set(true);
                foundPassword.set(password);
                return password;
            }
        }
        return null;
    }
}

