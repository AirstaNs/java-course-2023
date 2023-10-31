package edu.hw3;

import java.util.LinkedHashMap;
import java.util.Map;

@SuppressWarnings("MagicNumber")
public class Task4 {
    private static final Map<String, Integer> ROMAN_NUMBERS = new LinkedHashMap<>();

    static {
        ROMAN_NUMBERS.put("M", 1000);
        ROMAN_NUMBERS.put("CM", 900);
        ROMAN_NUMBERS.put("D", 500);
        ROMAN_NUMBERS.put("CD", 400);
        ROMAN_NUMBERS.put("C", 100);
        ROMAN_NUMBERS.put("XC", 90);
        ROMAN_NUMBERS.put("L", 50);
        ROMAN_NUMBERS.put("XL", 40);
        ROMAN_NUMBERS.put("X", 10);
        ROMAN_NUMBERS.put("IX", 9);
        ROMAN_NUMBERS.put("V", 5);
        ROMAN_NUMBERS.put("IV", 4);
        ROMAN_NUMBERS.put("I", 1);

    }

    private Task4() {
    }

    public static String convertToRoman(int number) {
        validateNumber(number);

        int copyNumber = number;
        StringBuilder result = new StringBuilder();

        for (var entry : ROMAN_NUMBERS.entrySet()) {
            int value = entry.getValue();
            String symbol = entry.getKey();

            while (copyNumber >= value) {
                copyNumber -= value;
                result.append(symbol);
            }
        }
        return result.toString();
    }

    private static void validateNumber(int number) {
        if (number <= 0 || number > 3999) {
            throw new IllegalArgumentException("Number out of range for Roman representation");
        }
    }
}
