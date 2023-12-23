package edu.module4.hw6.task2;

import edu.module4.hw6.task2.FileCloner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FileCloneUtilsTest {

    @Test
    @DisplayName("Клонирование файла: Создание одной копии")
    public void cloneFile_ShouldCreateFirstFileClone(@TempDir Path tempDirectory) throws IOException {
        Path originalFilePath = Files.createFile(tempDirectory.resolve("test.txt"));
        Path firstCopyPath = Path.of(originalFilePath.getParent().toString(), "test — копия.txt");

        FileCloner.cloneFile(originalFilePath);
        assertTrue(Files.exists(firstCopyPath));
    }

    @Test
    @DisplayName("Клонирование файла: Создание двух копий")
    public void cloneFile_ShouldCreateSecondFileClone(@TempDir Path tempDirectory) throws IOException {
        Path originalPath = Files.createFile(tempDirectory.resolve("test.txt"));
        Path firstCopyPath = Path.of(originalPath.getParent().toString(), "test — копия.txt");
        Path secondCopyPath = Path.of(originalPath.getParent().toString(), "test — копия (2).txt");

        FileCloner.cloneFile(originalPath);
        FileCloner.cloneFile(originalPath);
        assertTrue(Files.exists(firstCopyPath));
        assertTrue(Files.exists(secondCopyPath));
    }
}
