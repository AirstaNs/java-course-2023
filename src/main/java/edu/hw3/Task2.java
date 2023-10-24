package edu.hw3;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Task2 {
    private static final Map<Character, Character> BRACKET_PAIRS = Map.of('(', ')');

    private Task2() {
    }

    public static List<String> clusterize(String s) {
        Objects.requireNonNull(s);
        containsOnlyBrackets(s);
        List<String> clusters = new ArrayList<>();
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (BRACKET_PAIRS.containsKey(ch)) {
                stack.push(i);
            } else {
                int start = stack.pop();
                // Если стек стал пустым, это означает, что мы нашли сбалансированный кластер.
                if (stack.isEmpty()) {
                    clusters.add(s.substring(start, i + 1));
                }
            }
        }
        return clusters;
    }

    private static void containsOnlyBrackets(String str) {
        if (!str.matches("[()]+")) {
            throw new IllegalArgumentException("строка может содержать только круглые скобки \"()\"");
        }
    }
}
