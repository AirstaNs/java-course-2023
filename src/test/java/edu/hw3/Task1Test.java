package edu.hw3;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task1Test {

    private static List<Arguments> stringProvider() {
        return List.of(
            Arguments.of("Hello world!", "Svool dliow!"),
            Arguments.of("abcd", "zyxw"),
            Arguments.of("Привет, мир!", "Привет, мир!"),
            Arguments.of(
                "Any fool can write code that a computer can understand." +
                " Good programmers write code that humans can understand." +
                " ― Martin Fowler",

                "Zmb ullo xzm dirgv xlwv gszg z xlnkfgvi xzm fmwvihgzmw." +
                " Tllw kiltiznnvih dirgv xlwv gszg sfnzmh xzm fmwvihgzmw." +
                " ― Nzigrm Uldovi"
            )
        );
    }

    @ParameterizedTest
    @MethodSource("stringProvider")
    @DisplayName("Проверка Atbash шифрования")
    public void testAtbashEncryption(String input, String expectedOutput) {
        assertEquals(expectedOutput, Task1.atbash(input));
    }

    @Test
    @DisplayName("Обработка null-значений и пустой строки")
    public void testHandleNullOrEmptyInputs() {
        String empty = "";
        assertThrows(NullPointerException.class, () -> Task1.atbash(null));
        assertEquals(empty, Task1.atbash(empty));
    }
}
