package edu.hw8.task1;

import java.util.Map;

public final class Repository {
    private static final Map<String, String> QUOTES = Map.of(
        "личности", "Не переходи на личности там, где их нет",
        "оскорбления", "Если твои противники перешли на личные оскорбления, будь уверена — твоя победа не за горами",
        "интеллект", "Чем ниже интеллект, тем громче оскорбления"
    );

    private Repository() {
    }

    public static String findQuote(String quote) {
        return QUOTES.getOrDefault(quote, "не нашлось");
    }

    public static Map<String, String> getQuotes() {
        return QUOTES;
    }
}
