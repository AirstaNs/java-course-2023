package edu.module5.project4.image;

import edu.module5.project4.model.ImageFractal;
import edu.module5.project4.model.Pixel;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import java.util.UUID;
import javax.imageio.ImageIO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class ImageSaver {

    public final static Logger LOGGER = LogManager.getLogger();

    private ImageSaver() {
    }

    public static void save(ImageFractal image, Path src, ImageFormat format) throws IOException {
        BufferedImage bufferedImage = new BufferedImage(image.getWidth(),
            image.getHeight(),
            BufferedImage.TYPE_INT_RGB
        );

        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                Pixel pixel = image.getPixel(x, y);
                Color color = new Color(pixel.getRed(), pixel.getGreen(), pixel.getBlue());
                bufferedImage.setRGB(x, y, color.getRGB());
            }
        }
        String uuid = UUID.randomUUID().toString();
        Path path = src.resolve(String.format("%s.%s", uuid, format));
        LOGGER.info(path);
        ImageIO.write(bufferedImage, format.name(), path.toFile());
    }
}
