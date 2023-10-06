package edu.hw1;

import java.util.Objects;

public final class Task4 {
    private Task4() {
    }

    public static String fixString(String s) {
        Objects.requireNonNull(s);

        if (s.length() <= 1) {
            return s;
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < s.length() - 1; i += 2) {
            char curr = s.charAt(i);
            char next = s.charAt(i + 1);
            builder.append(next);
            builder.append(curr);
        }
        if (s.length() % 2 != 0) {
            builder.append(s.charAt(s.length() - 1));
        }
        return builder.toString();
    }
}
