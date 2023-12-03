package edu.hw8.task3;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.HexFormat;

public class PasswordCracker {

    private static final String ALPHABET = "0123456789abcdefghijklmnopqrstuvwxyz";
    private static final int ALPHABET_SIZE = ALPHABET.length();
    private final int maxLength;
    private final long maxIndex;
    private final AtomicBoolean found = new AtomicBoolean(false);

    public PasswordCracker(int maxLength) {
        this.maxLength = maxLength;
        this.maxIndex = calculateMaxIndex();
    }

    private long calculateMaxIndex() {
        long maxIndex = 0;
        for (int length = 1; length <= maxLength; length++) {
            maxIndex += Math.pow(ALPHABET_SIZE, length);
        }
        return maxIndex;
    }

    private String convertToPassword(long index, int length) {
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < length; i++) {
            password.insert(0, ALPHABET.charAt((int) (index % ALPHABET_SIZE)));
            index /= ALPHABET_SIZE;
        }
        return password.toString();
    }

    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hashInBytes = md.digest(password.getBytes());
            return HexFormat.of().formatHex(hashInBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error computing hash", e);
        }
    }

    public void crackPassword(String targetHash, int numberOfThreads) {
        try (ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads)) {
            long chunkSize = maxIndex / numberOfThreads;

            for (int i = 0; i < numberOfThreads; i++) {
                long start = i * chunkSize;
                long end = (i == numberOfThreads - 1) ? maxIndex : start + chunkSize;

                executor.submit(() -> {
                    for (long index = start; index < end && !found.get(); index++) {
                        String password = convertToIndexBasedPassword(index);
                        if (password != null && hashPassword(password).equals(targetHash)) {
                            found.set(true);
                            System.out.println("Password found: " + password);
                            break;
                        }
                    }
                });
            }
        }
    }

    private String convertToIndexBasedPassword(long index) {
        for (int length = 1; length <= maxLength; length++) {
            long maxIndexForLength = (long) Math.pow(ALPHABET_SIZE, length);
            if (index < maxIndexForLength) {
                return convertToPassword(index, length);
            }
            index -= maxIndexForLength;
        }
        return null;
    }

    public static void main(String[] args) {
        PasswordCracker cracker = new PasswordCracker(1);
        String targetHash = "45e4812014d83dde5666ebdf5a8ed1ed";
       cracker.crackPassword(targetHash, 1);
    }
}
