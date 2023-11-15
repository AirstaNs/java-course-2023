package edu.hw5;

import java.util.regex.Pattern;

public class Task5 {
    private static final Pattern PATTERN = Pattern.compile("[АВЕКМНОРСТУХ]\\d{3}[АВЕКМНОРСТУХ]{2}\\d{3}");

    private Task5() {}

    public static boolean isValidNumber(String autonumber) {
        return PATTERN.matcher(autonumber).matches();
    }
}
