package edu.project4;

import edu.project4.model.ImageFractal;
import edu.project4.model.Pixel;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ImageFractalTest {

    @Test
    void testCreate() {
        // Given
        int width = 5;
        int height = 5;

        // When
        ImageFractal fractal = ImageFractal.create(width, height);

        // Then
        assertNotNull(fractal);
        assertEquals(width, fractal.getWidth());
        assertEquals(height, fractal.getHeight());
    }

    @Test
    void testIsValidCoordinate() {
        // Given
        ImageFractal fractal = ImageFractal.create(5, 5);

        // When/Then
        assertTrue(fractal.isValidCoordinate(2, 2));
        assertFalse(fractal.isValidCoordinate(6, 6));
    }

    @Test
    void testGetPixel() {
        // Given
        ImageFractal fractal = ImageFractal.create(5, 5);

        // When
        Pixel pixel = fractal.getPixel(2, 2);

        // Then
        assertNotNull(pixel);
    }

    @Test
    void testGetPixelInvalidCoordinates() {
        // Given
        ImageFractal fractal = ImageFractal.create(5, 5);

        // When/Then
        assertThrows(IllegalArgumentException.class, () -> fractal.getPixel(6, 6));
    }
}
