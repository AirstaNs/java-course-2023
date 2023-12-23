package edu.module5.project4;

import edu.module5.project4.image.ImageFormat;
import edu.module5.project4.image.ImageSaver;
import edu.module5.project4.model.ImageFractal;
import edu.module5.project4.image.processor.GammaCorrectionProcessor;
import edu.module5.project4.renderer.ParallelRenderer;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Main {
    public final static Logger LOGGER = LogManager.getLogger();

    public final static Configuration CONFIG = Configuration.getDefault();

    private Main() {
    }

    public static void main(String[] args) throws IOException {
        var renderer = new ParallelRenderer(Runtime.getRuntime().availableProcessors(), CONFIG);
        LOGGER.info("start generating fractals");
        var fractal = ImageFractal.create(CONFIG.getImageWidth(), CONFIG.getImageHeight());
        fractal = renderer.render(fractal);

        new GammaCorrectionProcessor().process(fractal);
        ImageSaver.save(fractal, CONFIG.getOutputFolder(), ImageFormat.JPEG);
        LOGGER.info("end of generation");
    }
}
