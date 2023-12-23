package edu.module2.hw1;

public final class Task7 {

    private Task7() {
    }

    public static int rotateLeft(int number, int shift) {
        return rotate(number, shift, true);
    }

    public static int rotateRight(int number, int shift) {
        return rotate(number, shift, false);
    }

    private static int rotate(int number, int shift, boolean isLeft) {
        if (!isValid(number, shift)) {
            return Task1.INVALID_NUMBER;
        }

        int bitCount = countActiveBits(number);
        int newShift = shift % bitCount;
        int mask = (1 << bitCount) - 1;

        int result;
        if (isLeft) {
            result = (number << newShift) | (number >>> (bitCount - newShift));
        } else {
            result = (number >>> newShift) | (number << (bitCount - newShift));
        }
        return result & mask;
    }

    private static boolean isValid(int number, int shift) {
        return number > 0 && shift >= 0;
    }

    private static int countActiveBits(int number) {
        return Integer.toBinaryString(number).length();
    }
}
