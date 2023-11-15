package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class Task6Test {

    @Test
    @DisplayName("Должно возвращать true, если строка является подпоследовательностью другой строки")
    public void testIsSubstring_Positive() {
        // Given
        String str = "achfdbaabgabcaabg";
        String substr = "abc";

        // When
        boolean result = Task6.isSubstring(str, substr);

        // Then
        assertTrue(result);
    }

    @Test
    @DisplayName("Должно возвращать false, если строка не является подпоследовательностью другой строки")
    public void testIsSubstring_Negative() {
        // Given
        String str = "achfdbaabgabcaabg";
        String substr = "xyz";

        // When
        boolean result = Task6.isSubstring(str, substr);

        // Then
        assertFalse(result);
    }

    @Test
    @DisplayName("Должно возвращать true, если подстрока - пустая строка")
    public void testIsSubstring_EmptySubstring() {
        // Given
        String str = "achfdbaabgabcaabg";
        String substr = "";

        // When
        boolean result = Task6.isSubstring(str, substr);

        // Then
        assertTrue(result);
    }

    @Test
    @DisplayName("Должно корректно определять подстроку как регулярное выражение")
    public void testIsRegexSubstring() {
        // Given
        String originalString = "achfdbaabgabcaabg";

        // When & Then
        assertTrue(Task6.isSubstring(originalString, "a.*b.*c"));
        assertTrue(Task6.isSubstring(originalString, "a.*b.*c.*"));
        assertFalse(Task6.isSubstring(originalString, "a.*b.*c.*d"));
    }
}
