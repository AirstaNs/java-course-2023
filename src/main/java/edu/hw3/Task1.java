package edu.hw3;

import java.util.Objects;

public class Task1 {
    private static final char UPPER_A = 'A';
    private static final char UPPER_Z = 'Z';
    private static final char LOWER_A = 'a';
    private static final char LOWER_Z = 'z';

    private Task1() {
    }

    public static String atbash(String str) {
        Objects.requireNonNull(str);

        char[] charArray = str.toCharArray();
        StringBuilder builder = new StringBuilder(charArray.length);
        for (char c : charArray) {
            builder.append(getMirrorChar(c));
        }
        return builder.toString();
    }

    private static char getMirrorChar(char ch) {
        if (isUpperLatinLetter(ch)) {
            return (char) (UPPER_A + UPPER_Z - ch);
        } else if (isLowerLatinLetter(ch)) {
            return (char) (LOWER_A + LOWER_Z - ch);
        } else {
            return ch;
        }
    }

    private static boolean isUpperLatinLetter(char ch) {
        return (ch >= UPPER_A && ch <= UPPER_Z);
    }

    private static boolean isLowerLatinLetter(char ch) {
        return (ch >= LOWER_A && ch <= LOWER_Z);
    }
}
