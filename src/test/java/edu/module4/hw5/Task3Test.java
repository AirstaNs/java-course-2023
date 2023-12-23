package edu.module4.hw5;

import edu.module4.hw5.Task3;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task3Test {

    private static Stream<Arguments> dateStringsProvider() {
        return Stream.of(
            Arguments.of("2020-10-10", LocalDate.of(2020, 10, 10)),
            Arguments.of("2020-12-2", LocalDate.of(2020, 12, 2)),
            Arguments.of("1/3/1976", LocalDate.of(1976, 1, 3)),
            Arguments.of("1/3/20", LocalDate.of(2020, 1, 3)),
            Arguments.of("tomorrow", LocalDate.now().plusDays(1)),
            Arguments.of("today", LocalDate.now()),
            Arguments.of("yesterday", LocalDate.now().minusDays(1)),
            Arguments.of("1 day ago", LocalDate.now().minusDays(1)),
            Arguments.of("2234 days ago", LocalDate.now().minusDays(2234))
        );
    }

    @ParameterizedTest
    @MethodSource("dateStringsProvider")
    @DisplayName("Парсинг различных форматов дат")
    public void testParseDate(String dateString, LocalDate expected) {
        // When
        Optional<LocalDate> result = Task3.parseDate(dateString);

        // Then
        assertEquals(Optional.ofNullable(expected), result);
    }
}
