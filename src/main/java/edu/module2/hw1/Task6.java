package edu.module2.hw1;

import java.util.Arrays;

public final class Task6 {
    public static final int KAPREKAR_CONSTANT = 6174;
    private static final int MIN_BORDER = 1000;
    private static final int MAX_BORDER = 9999;

    private Task6() {
    }

    public static int countK(int num) {
        if (num <= MIN_BORDER || num > MAX_BORDER) {
            return Task1.INVALID_NUMBER;
        } else if (num == KAPREKAR_CONSTANT) {
            return 0;
        }

        char[] digits = String.format("%04d", num).toCharArray();
        Arrays.sort(digits);
        int lowerNumber = Integer.parseInt(String.valueOf(digits));
        reverse(digits);
        int largerNumber = Integer.parseInt(String.valueOf(digits));
        // проверяем что не все цифры одинаковы
        if (lowerNumber == largerNumber) {
            return Task1.INVALID_NUMBER;
        }
        return 1 + countK(largerNumber - lowerNumber);
    }

    private static void reverse(char[] array) {
        int length = array.length;
        for (int i = 0; i < length / 2; i++) {
            char temp = array[i];
            array[i] = array[length - 1 - i];
            array[length - 1 - i] = temp;
        }
    }
}
