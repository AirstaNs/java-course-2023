package edu.project4.image.processor;

import edu.project4.model.ImageFractal;
import edu.project4.model.Pixel;

public class GammaCorrectionProcessor implements ImageProcessor {
    private static final double GAMMA = 2.2;
    private static final double INVERSE_GAMMA = 1.0 / GAMMA;

    @Override
    public void process(ImageFractal image) {
        double maxNormalizationFactor = calculateMaxNormalizationFactor(image);
        applyGammaCorrection(image, maxNormalizationFactor);
    }

    private double calculateMaxNormalizationFactor(ImageFractal image) {
        double max = 0;
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                Pixel curPixel = image.getPixel(x, y);
                if (curPixel.getHitCount() != 0) {
                    curPixel.setNormalizationFactor(Math.log10(curPixel.getHitCount()));
                    max = Math.max(max, curPixel.getNormalizationFactor());
                }
            }
        }
        return max;
    }

    private void applyGammaCorrection(ImageFractal image, double maxNormalizationFactor) {
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                Pixel curPixel = image.getPixel(x, y);
                double normalizedFactor = curPixel.getNormalizationFactor() / maxNormalizationFactor;
                curPixel.adjustColors(normalizedFactor, INVERSE_GAMMA);
            }
        }
    }
}
