package edu.module5.project4.renderer;

import edu.module5.project4.Configuration;
import edu.module5.project4.model.AffineParameters;
import edu.module5.project4.model.ImageFractal;

public class SimpleRenderer extends AbstractRenderer {

    protected SimpleRenderer(Configuration configuration) {
        super(configuration);
    }

    @Override
    public ImageFractal render(ImageFractal canvas) {
        int samples = config.getSamples();
        AffineParameters[] coefficientsArray = getRandomAffineCoefficients(samples);
        for (int num = 0; num < samples; num++) {
            this.renderSample(canvas, coefficientsArray[num]);
        }
        return canvas;
    }
}

