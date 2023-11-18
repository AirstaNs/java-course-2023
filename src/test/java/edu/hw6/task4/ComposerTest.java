package edu.hw6.task4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ComposerTest {
    @Test
    @DisplayName("Тестирование записи фразы с помощью Composer")
    public void write_shouldWriteToFile() throws IOException {
        String expected = "Programming is learned by writing programs. ― Brian Kernighan";
        Path path = Files.createTempFile("test", ".txt");
        Composer.write(path);
        assertEquals(expected, Files.readString(path).strip());
    }
}
