package edu.module5.hw8.task3;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

public class PasswordCracker {
    private final AtomicBoolean found = new AtomicBoolean(false);

    private final PasswordGenerator passwordGenerator;

    public PasswordCracker(PasswordGenerator generator) {
        this.passwordGenerator = generator;
    }

    public String crackPassword(String targetHash, int numberOfThreads) {
        try (ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads)) {
            long totalCombinations = passwordGenerator.getTotalCombinations();
            long chunkSize = totalCombinations / numberOfThreads;

            CompletableFuture<?>[] tasks = new CompletableFuture[numberOfThreads];
            CompletableFuture<String> foundFuture = new CompletableFuture<>();

            byte[] targetBytes = HashUtils.hexStringToByteArray(targetHash);
            for (int i = 0; i < numberOfThreads; i++) {
                final long start = i * chunkSize;
                final long end = (i == numberOfThreads - 1) ? totalCombinations : start + chunkSize;
                tasks[i] = CompletableFuture.supplyAsync(() -> passwordSearch(targetBytes, start, end), executor)
                                            .thenAccept(result -> {
                                                if (result != null) {
                                                    foundFuture.complete(result);
                                                }
                                            });
            }
            var allOf = CompletableFuture.allOf(tasks);
            var anyResult = CompletableFuture.anyOf(allOf, foundFuture);

            return (String) anyResult.join();
        }
    }

    private String passwordSearch(byte[] targetBytes, long start, long end) {
        for (long index = start; index < end && !found.get(); index++) {

            char[] chars = passwordGenerator.convertToIndexBasedPassword(index);
            if (chars == null) {
                return null;
            }

            String password = String.valueOf(chars);
            if (Arrays.equals(HashUtils.hashPassword(password), targetBytes)) {
                found.set(true);
                return password;
            }
        }
        return null;
    }
}

