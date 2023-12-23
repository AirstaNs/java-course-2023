package edu.module4.hw5;

import edu.module4.hw5.Task6;
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
        String sub = "abc";

        // When
        boolean result = Task6.isSubstring(sub, str);

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
        boolean result = Task6.isSubstring(substr, str);

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
        boolean result = Task6.isSubstring(substr, str);

        // Then
        assertTrue(result);
    }

    @Test
    @DisplayName("Должно корректно определять подстроку как регулярное выражение")
    public void testIsRegexSubstring() {
        // Given
        String originalString = "achfdbaabgabcaabg";

        // When & Then
        assertFalse(Task6.isSubstring(originalString, "a.*b.*c"));
    }

    @Test
    @DisplayName("Должно корректно определять подстроку как регулярное выражение")
    public void testIsRegexSubstringTwo() {
        // Given
        String str = "as\\dfdsa";
        String sub = "\\df";

        // When & Then
        assertTrue(Task6.isSubstring(sub, str));
    }
}
