package edu.hw1;

import java.util.Objects;

public final class Task1 {

    public static final int INVALID_DURATION = -1;
    private static final String DELIMITER = ":";
    private static final long MAX_SECONDS_PER_MINUTE = 60;

    private Task1() {
    }

    public static long minutesToSeconds(String s) {
        Objects.requireNonNull(s);
        // если строка не содержит разделителя, в нашем случае ":", то сразу возвращаем -1
        if (!s.contains(DELIMITER)) {
            return INVALID_DURATION;
        }

        String[] parts = s.split(DELIMITER);
        // если разделенная строка не содержит двух частей - секунд и минут, то возвращаем -1
        if (parts.length != 2) {
            return INVALID_DURATION;
        }

        // Проверяем, состоят ли обе части только из цифр
        if (!arePartsDigits(parts)) {
            return INVALID_DURATION;
        }

        return calculateTotalSeconds(parts);
    }

    private static long calculateTotalSeconds(String[] parts) {
        int minutes = Integer.parseInt(parts[0]);
        int seconds = Integer.parseInt(parts[1]);

        // если кол-во секунд больше или равно 60, то возвращаем -1
        if (seconds >= MAX_SECONDS_PER_MINUTE) {
            return INVALID_DURATION;
        }
        return minutes * MAX_SECONDS_PER_MINUTE + seconds;
    }

    // Проверяем, состоят ли обе части только из цифр
    private static boolean arePartsDigits(String[] parts) {
        for (String part : parts) {
            for (char ch : part.toCharArray()) {
                if (!Character.isDigit(ch)) {
                    return false;
                }
            }
        }
        return true;
    }
}
