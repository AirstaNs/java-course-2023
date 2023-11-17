package edu.hw5;

import java.util.regex.Pattern;

public final class Task4 {

    private static final Pattern PATTERN = Pattern.compile(".*[~!@#$%^&*|].*");

    private Task4() {}

    public static boolean isValidatePassword(String password) {
        return PATTERN.matcher(password).matches();
    }
}
