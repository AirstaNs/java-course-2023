package edu.hw1;

public final class Task2 {
    private static final int BASE = 10;

    private Task2() {
    }

    public static int countDigits(long number) {
        int count = 0;
        long tempNumber = number; // создаем копию числа, чтобы не менять оригинальное значение

        do {
            tempNumber /= BASE;
            count++;
        } while (tempNumber != 0);

        return count;
    }
}
