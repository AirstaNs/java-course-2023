package edu.hw9;

import edu.hw9.task2.DirectorySearcher;
import edu.hw9.task2.DiskCollector;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Comparator;
import java.util.concurrent.ForkJoinPool;
import java.util.function.BiPredicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DirectorySearcherTest {

    private Path testDirectory;

    private static void deleteDirectoryRecursively(Path path) throws IOException {
        try (Stream<Path> walk = Files.walk(path)) {
            walk.sorted(Comparator.reverseOrder())
                .forEach(p -> {
                    try {
                        Files.delete(p);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
        }
    }

    @BeforeEach
    public void setup() throws Exception {
        testDirectory = Files.createTempDirectory("testDir");
        createTestFiles(testDirectory, 10);
        createSubdirectoryWithFiles(testDirectory, "subdir1", 10);
        createSubdirectoryWithFiles(testDirectory, "subdir2", 10);
    }

    @AfterEach
    public void cleanup() throws Exception {
        deleteDirectoryRecursively(testDirectory);
    }

    private void createTestFiles(Path directory, int fileCount) {
        IntStream.range(0, fileCount)
            .forEach(i -> {
                try {
                    Files.createFile(directory.resolve("file" + i + ".txt"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    private void createSubdirectoryWithFiles(
        Path parentDirectory, String subDirName, int fileCount
    ) throws IOException {
        Path subDirectory = Files.createDirectory(parentDirectory.resolve(subDirName));
        createTestFiles(subDirectory, fileCount);
    }

    @Test
    public void testDirectorySearcher() {
        BiPredicate<Path, Long> dirPredicate = (path, count) -> count >= 28;
        BiPredicate<Path, BasicFileAttributes> filePredicate = (path, attr) -> path.getFileName()
                                                                                  .toString()
                                                                                  .equals("file9.txt");

        var dirCollector = new DiskCollector<>(dirPredicate);
        var fileCollector = new DiskCollector<>(filePredicate);

        DirectorySearcher task = new DirectorySearcher(testDirectory, dirCollector, fileCollector);

        Long result;
        try (ForkJoinPool pool = ForkJoinPool.commonPool()) {
            result = pool.invoke(task);
        }

        assertEquals(30, result);
        assertEquals(1, dirCollector.getMatchingPaths().size());
        assertEquals(3, fileCollector.getMatchingPaths().size());

    }
}
