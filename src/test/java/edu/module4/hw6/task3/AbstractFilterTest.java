package edu.module4.hw6.task3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import edu.module4.hw6.task3.AbstractFilter;
import edu.module4.hw6.task3.FileFilters;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AbstractFilterTest {

    @TempDir
    Path tempDir;

    @Test
    void largerThan_ShouldReturnTrueForLargeFile() throws IOException {
        Path largeFile = Files.createTempFile(tempDir, "largeFile", ".txt");
        Files.writeString(largeFile, "Some large content");

        assertTrue(FileFilters.largerThan(10).accept(largeFile));
    }

    @Test
    void globMatches_ShouldReturnTrueForMatchingPattern() throws IOException {
        Path textFile = Files.createTempFile(tempDir, "sample", ".txt");

        assertTrue(FileFilters.globMatches("*.txt").accept(textFile));
    }

    @Test
    void regexContains_ShouldReturnTrueForMatchingRegex() throws IOException {
        Path sampleFile = Files.createTempFile(tempDir, "sample123", ".txt");

        assertTrue(FileFilters.regexContains(".*123.*").accept(sampleFile));
    }

    @Test
    void magicNumber_ShouldReturnTrueForMatchingMagicNumber() throws IOException {
        Path magicNumberFile = Files.createTempFile(tempDir, "magic", ".bin");
        Files.write(magicNumberFile, new byte[]{(byte) 0x89, 'P', 'N', 'G'});

        assertTrue(FileFilters.magicNumber(0x89, 'P', 'N', 'G').accept(magicNumberFile));
    }

    @Test
    void combinedFilters_ShouldWorkCorrectly() throws IOException {
        Path largeTextFile = Files.createTempFile(tempDir, "largeTextFile", ".txt");
        Files.writeString(largeTextFile, String.format("%0" + 2000 + "d", 0));

        AbstractFilter largeFiles = FileFilters.largerThan(1000);
        AbstractFilter txtFiles = FileFilters.globMatches("*.txt");

        AbstractFilter combinedFilter = largeFiles.and(txtFiles);

        assertTrue(combinedFilter.accept(largeTextFile));
    }
}
