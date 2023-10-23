package edu.hw1;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task0Test {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent, true, StandardCharsets.UTF_8));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    @DisplayName("Проверка вывода сообщения 'Привет, мир!'")
    public void testPrintHelloWorld() {
        String input = "Привет, мир!";
        Task0.printHelloWorld();

        String actualOutput = outContent.toString(StandardCharsets.UTF_8).trim();
        System.out.println("Actual output: '" + actualOutput + "'");
        assertTrue(actualOutput.contains(input));
    }

}
