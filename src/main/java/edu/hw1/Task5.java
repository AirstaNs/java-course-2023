package edu.hw1;

public final class Task5 {
    private Task5() {
    }

    public static boolean isPalindromeDescendant(long num) {
        long abs = Math.abs(num);
        String str = Long.toString(abs);
        if (isPalindrome(str)) {
            return true;
        }

        // если число меньше 10 или длинна нечетная, то выходим из цикла
        while ((str.length() > 1) && (str.length() % 2 == 0)) {
            str = createDescendant(str);
            if (isPalindrome(str)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isPalindrome(String str) {
        if (str.length() <= 1) {
            return false;
        }
        int start = 0;
        int end = str.length() - 1;

        while (start < end) {
            char left = str.charAt(start);
            char right = str.charAt(end);
            if (left != right) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

    private static String createDescendant(String string) {
        int length = string.length();
        StringBuilder builder = new StringBuilder(length / 2);

        for (int i = 0; i < length - 1; i += 2) {
            int left = Character.getNumericValue(string.charAt(i));
            int right = Character.getNumericValue(string.charAt(i + 1));
            int sum = left + right;
            builder.append(sum);
        }
        return builder.toString();
    }
}
