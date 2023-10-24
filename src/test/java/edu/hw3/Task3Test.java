package edu.hw3;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task3Test {

    private static List<Arguments> listProvider() {
        return List.of(
            Arguments.of(
                List.of("a", "bb", "a", "bb"),
                Map.of("bb", 2, "a", 2)
            ),
            Arguments.of(
                List.of("this", "and", "that", "and"),
                Map.of("that", 1, "and", 2, "this", 1)
            ),
            Arguments.of(
                List.of("код", "код", "код", "bug"),
                Map.of("код", 3, "bug", 1)
            ),
            Arguments.of(
                List.of(1, 1, 2, 2),
                Map.of(1, 2, 2, 2)
            )
        );
    }

    @ParameterizedTest
    @MethodSource("listProvider")
    @DisplayName("Проверка подсчета частоты")
    void validateFrequencyDictionary(List<?> inputList, Map<?, ?> expected) {
        // When
        Map<?, ?> result = Task3.freqDict(inputList);

        // Then
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Тест невалидных значений")
    void testPalindromeOnNormalData5() {
        assertThrows(NullPointerException.class, () -> Task3.freqDict(null));
    }
}
