package edu.project4;

import edu.project4.model.Rectangle;
import edu.project4.transformation.TransformationType;
import edu.project4.transformation.Transformations;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@SuppressWarnings("checkstyle:MagicNumber")
public final class Configuration {

    private final Transformations transformations;

    private final int imageHeight;
    private final int imageWidth;

    private final Rectangle rectangle;
    private final int samples;
    private final int iterationsPerSample;
    private final int symmetry;

    private final Path outputFolder;

    public static Configuration getDefault() {
        var popcorn = TransformationType.Popcorn.create(1.7, 0.3);
        var multiplication = TransformationType.Complex.create(1.2, -2.19);

        return Configuration.builder()
            .transformations(Transformations.all(popcorn, multiplication))
            .imageHeight(1080)
            .imageWidth(1920)
            .rectangle(new Rectangle(-4, -3, 8, 6))
            .samples(2)
            .iterationsPerSample(50_000)
            .symmetry(12)
            .outputFolder(getOutputFolderPath())
            .build();
    }

    public static Path getOutputFolderPath() {
        try {
            URL resourceFolderUrl = Configuration.class.getClassLoader().getResource("");
            if (resourceFolderUrl == null) {
                throw new IllegalStateException("Resources folder not found");
            }
            return Paths.get(resourceFolderUrl.toURI());

        } catch (URISyntaxException e) {
            throw new RuntimeException("Error while accessing resources folder", e);
        }
    }
}
