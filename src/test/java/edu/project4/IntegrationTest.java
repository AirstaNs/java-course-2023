package edu.project4;

import edu.project4.image.ImageFormat;
import edu.project4.image.ImageSaver;
import edu.project4.image.processor.GammaCorrectionProcessor;
import edu.project4.model.ImageFractal;
import edu.project4.model.Rectangle;
import edu.project4.renderer.ParallelRenderer;
import edu.project4.transformation.TransformationType;
import edu.project4.transformation.Transformations;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IntegrationTest {

    @Test
    void testFractalGenerationProcess(@TempDir Path tempDir) throws IOException {
        // Given
        var popcorn = TransformationType.Popcorn.create(1.7, 0.3);
        var multiplication = TransformationType.Complex.create(1.2, -2.19);

        Configuration config = Configuration.builder()
            .transformations(Transformations.all(popcorn, multiplication))
            .imageHeight(1080)
            .imageWidth(1920)
            .rectangle(new Rectangle(-4, -3, 8, 6))
            .samples(2)
            .iterationsPerSample(50_000)
            .symmetry(12)
            .outputFolder(tempDir)
            .build();

        // When
        try (var renderer = new ParallelRenderer(Runtime.getRuntime().availableProcessors(), config)) {
            var fractal = ImageFractal.create(config.getImageWidth(), config.getImageHeight());
            fractal = renderer.render(fractal);

            new GammaCorrectionProcessor().process(fractal);
            ImageSaver.save(fractal, config.getOutputFolder(), ImageFormat.JPEG);
        }

        // Then
        try (Stream<Path> list = Files.list(tempDir)) {
            Path savedFile = list.findFirst().orElseThrow();
            assertTrue(Files.exists(savedFile));
            assertTrue(savedFile.toString().endsWith(ImageFormat.JPEG.toString()));
            assertTrue(Files.size(savedFile) > 0);
        }
    }
}
