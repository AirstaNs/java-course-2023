package edu.project3.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FormatTest {

    @Test
    @DisplayName("Корректный разбор формата ADOC")
    public void testParseAdoc() {
        assertEquals(Format.ADOC, Format.parse("adoc"));
    }

    @Test
    @DisplayName("Корректный разбор формата MARKDOWN")
    public void testParseMarkdown() {
        assertEquals(Format.MARKDOWN, Format.parse("markdown"));
    }

    @Test
    @DisplayName("Генерация исключения при неверном формате")
    public void testInvalidFormat() {
        assertThrows(IllegalArgumentException.class, () -> Format.parse("invalid"));
    }

    @Test
    @DisplayName("Генерация исключения")
    public void testNullFormat() {
        assertThrows(NullPointerException.class, () -> Format.parse(null));
    }

    @Test
    @DisplayName("Учитывание регистра в форматах")
    public void testCaseSensitivity() {
        assertEquals(Format.ADOC, Format.parse("Adoc"));
        assertEquals(Format.MARKDOWN, Format.parse("Markdown"));
    }
}
