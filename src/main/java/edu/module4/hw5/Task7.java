package edu.module4.hw5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task7 {

    private static final Pattern PATTERN1 = Pattern.compile("^[01]{2}0[01]*$");
    private static final Pattern PATTERN2 = Pattern.compile("^([01]).*\\1$");
    private static final Pattern PATTERN3 = Pattern.compile("^[01]{1,3}$");

    private Task7() {
    }

    public static boolean containsAtLeastThreeZeros(String input) {
        Matcher matcher = PATTERN1.matcher(input);
        return matcher.matches();
    }

    public static boolean startsAndEndsWithSameSymbol(String input) {
        Matcher matcher = PATTERN2.matcher(input);
        return matcher.matches();
    }

    public static boolean hasLengthBetweenOneAndThree(String input) {
        Matcher matcher = PATTERN3.matcher(input);
        return matcher.matches();
    }
}
