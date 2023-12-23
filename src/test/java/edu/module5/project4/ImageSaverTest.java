package edu.module5.project4;

import edu.module5.project4.image.ImageFormat;
import edu.module5.project4.image.ImageSaver;
import edu.module5.project4.model.ImageFractal;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ImageSaverTest {

    @Test
    void testSave(@TempDir Path tempDir) throws IOException {
        // Given
        int width = 100, height = 100;
        ImageFractal image = ImageFractal.create(width, height);
        ImageFormat format = ImageFormat.PNG;

        // When
        ImageSaver.save(image, tempDir, format);

        // Then
        try (Stream<Path> list = Files.list(tempDir)) {
            Path savedFile = list.findFirst().orElseThrow();
            assertTrue(Files.exists(savedFile));
            assertTrue(savedFile.toString().endsWith(format.toString()));
            assertTrue(Files.size(savedFile) > 0);
        }
    }
}
