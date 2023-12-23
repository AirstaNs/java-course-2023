package edu.module4.hw5;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public final class Task3 {

    private static final String[] DATE_FORMATS = {
        "yyyy-MM-dd",
        "M/d/yyyy",
        "M/d/yy",
        "yyyy-MM-d"
    };

    private Task3() {
    }

    public static Optional<LocalDate> parseDate(String string) {
        Optional<LocalDate> parsedDate;

        for (String format : DATE_FORMATS) {
            parsedDate = tryParse(string, DateTimeFormatter.ofPattern(format));
            if (parsedDate.isPresent()) {
                return parsedDate;
            }
        }

        parsedDate = parseRelativeDate(string);

        return parsedDate;
    }

    private static Optional<LocalDate> tryParse(String string, DateTimeFormatter formatter) {
        try {
            return Optional.of(LocalDate.parse(string, formatter));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    private static Optional<LocalDate> parseRelativeDate(String string) {
        String copyString = string.trim().toLowerCase();
        LocalDate parse;
        if (copyString.matches("\\d+\\s+(day|days)\\s+ago")) {
            int daysAgo = Integer.parseInt(copyString.split("\\s+")[0]);
            parse = LocalDate.now().minusDays(daysAgo);
        } else {
            LocalDate now = LocalDate.now();
            parse = switch (copyString) {
                case "tomorrow" -> now.plusDays(1);
                case "today" -> now;
                case "yesterday" -> now.minusDays(1);
                default -> null;
            };
        }
        return Optional.ofNullable(parse);
    }
}
