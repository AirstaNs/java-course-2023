package edu.hw5;

public class Task6 {

    private static final String FORMAT = ".*%s.*";

    private Task6() {
    }

    public static boolean isSubstring(String str, String substr) {
        String formatted = FORMAT.formatted(substr);
        return str.matches(formatted);
    }
}
