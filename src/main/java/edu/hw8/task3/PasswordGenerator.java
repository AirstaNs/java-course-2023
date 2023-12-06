package edu.hw8.task3;

public class PasswordGenerator {
    private static final String ALPHABET = "0123456789abcdefghijklmnopqrstuvwxyz";
    private static final int ALPHABET_SIZE = ALPHABET.length();
    private final long[] powers;
    private final int maxLength;

    private final long totalCombinations;

    public PasswordGenerator(int maxLength) {
        this.maxLength = maxLength;
        this.powers = precomputePowers();
        this.totalCombinations = powers[maxLength - 1] * ALPHABET_SIZE;
    }

    private long[] precomputePowers() {
        long[] localPowers = new long[maxLength + 1];
        for (int i = 0; i <= maxLength; i++) {
            localPowers[i] = (i == 0) ? 1 : localPowers[i - 1] * ALPHABET_SIZE;
        }
        return localPowers;
    }

    public String convertToIndexBasedPassword(long index) {
        long copyIndex = index;
        for (int length = 1; length <= maxLength; length++) {
            long maxIndexForLength = powers[length - 1] * ALPHABET_SIZE;
            if (copyIndex < maxIndexForLength) {
                return convertToPassword(copyIndex, length);
            }
            copyIndex -= maxIndexForLength;
        }
        return null;
    }

    private String convertToPassword(long index, int length) {
        long copyIndex = index;
        int copyLength = length;
        StringBuilder password = new StringBuilder(copyLength);
        while (copyLength-- > 0) {
            password.append(ALPHABET.charAt((int) (copyIndex % ALPHABET_SIZE)));
            copyIndex /= ALPHABET_SIZE;
        }
        return password.toString();
    }

    public long getTotalCombinations() {
        return totalCombinations;
    }
}
