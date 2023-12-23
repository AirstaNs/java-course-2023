package edu.module3.hw3;

import java.util.List;

import edu.module3.hw3.Task2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task2Test {
    private static List<Arguments> bracketsProvider() {
        return List.of(
            Arguments.of("()()()", List.of("()", "()", "()")),
            Arguments.of("((()))", List.of("((()))")),
            Arguments.of("((()))(())()()(()())", List.of("((()))", "(())", "()", "()", "(()())")),
            Arguments.of("((())())(()(()()))", List.of("((())())", "(()(()()))"))
        );
    }

    @ParameterizedTest
    @MethodSource("bracketsProvider")
    @DisplayName("Проверка разделения скобок шифрования")
    void testValidateBracketClustering(String input, List<String> expected) {
        // When
        List<String> result = Task2.clusterize(input);

        // Then
        assertEquals(expected.size(), result.size());
        for (int i = 0; i < result.size(); i++) {
            assertEquals(expected.get(i), result.get(i));
        }
    }

    @Test
    @DisplayName("Обработка специальных входных данных в clusterize")
    public void testHandleSpecialInputsForClusterize() {
        // Given
        String empty = "";

        // When & Then
        assertThrows(NullPointerException.class, () -> Task2.clusterize(null));
        assertThrows(IllegalArgumentException.class, () -> Task2.clusterize(empty));
        assertThrows(IllegalArgumentException.class, () -> Task2.clusterize("1"));
        assertThrows(IllegalArgumentException.class, () -> Task2.clusterize("(в)"));
    }
}
