package edu.hw5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task6 {

    private static final String FORMAT = ".*%s.*";

    private Task6() {
    }

    public static boolean isSubstring(String s, String t) {
        StringBuilder regex = new StringBuilder();
        for (char ch : s.toCharArray()) {
            regex.append(".*").append(Pattern.quote(String.valueOf(ch)));
        }

        Pattern pattern = Pattern.compile(regex.toString());
        Matcher matcher = pattern.matcher(t);

        return matcher.find();
    }
}
