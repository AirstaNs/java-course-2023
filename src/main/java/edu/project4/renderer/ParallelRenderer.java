package edu.project4.renderer;

import edu.project4.Configuration;
import edu.project4.model.AffineParameters;
import edu.project4.model.ImageFractal;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ParallelRenderer extends AbstractRenderer implements AutoCloseable {
    private final ExecutorService executorService;

    public ParallelRenderer(int numThreads, Configuration config) {
        super(config);
        executorService = Executors.newFixedThreadPool(
            numThreads > 0 ? numThreads : Runtime.getRuntime().availableProcessors());
    }

    @Override
    public ImageFractal render(ImageFractal canvas) {
        var samples = config.getSamples();
        AffineParameters[] coefficientsArray = getRandomAffineCoefficients(samples);
        for (int num = 0; num < samples; num++) {
            final AffineParameters coefficients = coefficientsArray[num];
            executorService.execute(() -> renderSample(canvas, coefficients));
        }
        return canvas;
    }

    @Override
    public void close() {
        executorService.close();
    }
}

