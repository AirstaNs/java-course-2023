package edu.hw5;

import java.util.regex.Pattern;

public class Task5 {
    private static final Pattern PATTERN = Pattern.compile("[А-ЯеЁ]\\d{3}[А-ЯеЁ]{2}\\d{3}");

    private Task5() {}

    public static boolean isValidateNumber(String password) {
        return PATTERN.matcher(password).matches();
    }
}
