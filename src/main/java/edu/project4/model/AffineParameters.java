package edu.project4.model;

import java.awt.Color;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public record AffineParameters(double a, double b, double c, double d, double e, double f, Color color) {
    private static final int MAX_COLOR_VALUE = 256;
    private static final int MAX_ATTEMPTS = 100_000;

    private static Color randomColor() {
        Random random = ThreadLocalRandom.current();
        return new Color(random.nextInt(MAX_COLOR_VALUE),
            random.nextInt(MAX_COLOR_VALUE),
            random.nextInt(MAX_COLOR_VALUE)
        );
    }

    public static AffineParameters create() {
        Random random = ThreadLocalRandom.current();
        for (int attempt = 0; attempt < MAX_ATTEMPTS; attempt++) {
            double a = random.nextDouble(-1, 1);
            double b = random.nextDouble(-1, 1);
            double c = random.nextDouble(-1, 1);
            double d = random.nextDouble(-1, 1);
            double e = random.nextDouble(-1, 1);
            double f = random.nextDouble(-1, 1);

            if (isValid(a, b, c, d, e, f)) {
                return new AffineParameters(a, b, c, d, e, f, randomColor());
            }
        }
        return new AffineParameters(0, 0, 0, 0, 0, 0, randomColor());
    }

    private static boolean isValid(double a, double b, double c, double d, double e, double f) {
        return (a * a + d * d < 1) && (b * b + e * e < 1)
               && (a * a + b * b + d * d + e * e < 1 + (a * e - b * d) * (a * e - b * d));
    }
}
