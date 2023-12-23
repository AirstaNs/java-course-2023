package edu.module4.project3.model;

import edu.module4.project3.model.Argument;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ArgumentTest {

    @Test
    @DisplayName("Корректный разбор аргумента PATH")
    public void testParsePath() {
        assertEquals(Argument.PATH, Argument.parse("--path"));
    }

    @Test
    @DisplayName("Корректный разбор аргумента FROM")
    public void testParseFrom() {
        assertEquals(Argument.FROM, Argument.parse("--from"));
    }

    @Test
    @DisplayName("Корректный разбор аргумента TO")
    public void testParseTo() {
        assertEquals(Argument.TO, Argument.parse("--to"));
    }

    @Test
    @DisplayName("Корректный разбор аргумента FORMAT")
    public void testParseFormat() {
        assertEquals(Argument.FORMAT, Argument.parse("--format"));
    }

    @Test
    @DisplayName("Генерация исключения при неверном аргументе")
    public void testInvalidArgument() {
        assertThrows(IllegalArgumentException.class, () -> Argument.parse("--invalid"));
    }

    @Test
    @DisplayName("Генерация исключения при пустом аргументе")
    public void testNullArgument() {
        assertThrows(NullPointerException.class, () -> Argument.parse(null));
    }

    @Test
    @DisplayName("Учитывание регистра и префикса в аргументах")
    public void testCaseAndPrefixSensitivity() {
        assertEquals(Argument.PATH, Argument.parse("Path"));
        assertEquals(Argument.FROM, Argument.parse("FROM"));
        assertEquals(Argument.TO, Argument.parse("To"));
        assertEquals(Argument.FORMAT, Argument.parse("format"));
    }
}
