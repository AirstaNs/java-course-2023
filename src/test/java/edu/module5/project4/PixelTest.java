package edu.module5.project4;

import edu.module5.project4.model.Pixel;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PixelTest {

    @Test
    void testAdjustColors() {
        // Given
        Pixel pixel = new Pixel(100, 150, 200, 10, 1.0);
        double normalizationFactor = 0.5;
        double inverseGamma = 1.0;

        // When
        pixel.adjustColors(normalizationFactor, inverseGamma);

        // Then
        assertEquals(50, pixel.getRed());
        assertEquals(75, pixel.getGreen());
        assertEquals(100, pixel.getBlue());
    }
}
