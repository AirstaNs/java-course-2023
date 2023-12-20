package edu.project4.renderer;

import edu.project4.Configuration;
import edu.project4.model.AffineParameters;
import edu.project4.model.ImageFractal;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;

public class ParallelRenderer extends AbstractRenderer {
    private final int numThreads;

    public ParallelRenderer(int numThreads, Configuration config) {
        super(config);
        this.numThreads = numThreads > 0 ? numThreads : Runtime.getRuntime().availableProcessors();
    }

    @Override
    public ImageFractal render(ImageFractal canvas) {
        try (var service = Executors.newFixedThreadPool(this.numThreads)) {

            var samples = config.getSamples();
            AffineParameters[] coefficientsArray = getRandomAffineCoefficients(samples);
            CountDownLatch latch = new CountDownLatch(samples);
            for (int num = 0; num < samples; num++) {
                final AffineParameters coefficients = coefficientsArray[num];
                service.execute(() -> {
                    try {
                        renderSample(canvas, coefficients);
                    } finally {
                        latch.countDown();
                    }
                });
            }
            latch.await();
            return canvas;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

