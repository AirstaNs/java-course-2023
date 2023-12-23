package edu.module5.project4.renderer;

import edu.module5.project4.Configuration;
import edu.module5.project4.transformation.types.Transformation;
import edu.module5.project4.model.AffineParameters;
import edu.module5.project4.model.ImageFractal;
import edu.module5.project4.model.Pixel;
import edu.module5.project4.model.Point;
import edu.module5.project4.model.Rectangle;
import java.util.concurrent.ThreadLocalRandom;

public abstract class AbstractRenderer implements Renderer {

    protected static final int START = -20;

    protected final Configuration config;

    protected AbstractRenderer(Configuration configuration) {
        this.config = configuration;
    }

    protected void renderSample(ImageFractal canvas, AffineParameters coff) {
        var rectangle = config.getRectangle();
        var transformations = config.getTransformations();
        var iterations = config.getIterationsPerSample();
        var symmetry = config.getSymmetry();

        Point p = rectangle.randomPoint();
        var list = transformations.getTransformations();
        for (int step = START; step < iterations; step++) {
            p = applyAffineTransformation(coff, p);

            Transformation transformation = list.get(ThreadLocalRandom.current().nextInt(list.size()));
            p = transformation.apply(p);

            if (step >= 0) {
                double thetaIncrement = 2 * Math.PI / symmetry;
                for (int s = 0; s < symmetry; s++) {
                    double theta = s * thetaIncrement;
                    Point pwr = rotatePoint(p, theta);
                    if (rectangle.contains(pwr)) {
                        this.updatePixel(canvas, pwr, rectangle, coff);
                    }
                }
            }
        }
    }

    protected Point rotatePoint(Point pw, double theta) {
        double xRot = pw.x() * Math.cos(theta) - pw.y() * Math.sin(theta);
        double yRot = pw.x() * Math.sin(theta) + pw.y() * Math.cos(theta);
        return new Point(xRot, yRot);
    }

    protected Point applyAffineTransformation(AffineParameters coefficients, Point pw) {
        double x = coefficients.a() * pw.x() + coefficients.b() * pw.y() + coefficients.c();
        double y = coefficients.d() * pw.x() + coefficients.e() * pw.y() + coefficients.f();
        return new Point(x, y);
    }

    protected void updatePixel(ImageFractal canvas, Point pwr, Rectangle world, AffineParameters coefficients) {
        int canvasX = (int) ((pwr.x() - world.x()) * canvas.getWidth() / world.width());
        int canvasY = (int) ((pwr.y() - world.y()) * canvas.getHeight() / world.height());
        Pixel pixel = canvas.getPixel(canvasX, canvasY);
        synchronized (pixel) {
            this.blendPixelColor(pixel, coefficients);
            pixel.setHitCount(pixel.getHitCount() + 1);
        }
    }

    protected void blendPixelColor(Pixel pixel, AffineParameters coefficients) {
        if (pixel.getHitCount() == 0) {
            pixel.setRed(coefficients.color().getRed());
            pixel.setGreen(coefficients.color().getGreen());
            pixel.setBlue(coefficients.color().getBlue());
        } else {
            pixel.setRed((pixel.getRed() + coefficients.color().getRed()) / 2);
            pixel.setGreen((pixel.getGreen() + coefficients.color().getGreen()) / 2);
            pixel.setBlue((pixel.getBlue() + coefficients.color().getBlue()) / 2);
        }
    }

    protected AffineParameters[] getRandomAffineCoefficients(int samples) {
        AffineParameters[] transformations = new AffineParameters[samples];
        for (int i = 0; i < samples; i++) {
            transformations[i] = AffineParameters.create();
        }
        return transformations;
    }
}

