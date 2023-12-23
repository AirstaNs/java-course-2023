package edu.project4;

import edu.project4.model.Point;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PointTest {

    @Test
    void testGetRadius() {
        // Given
        Point point = new Point(3, 4);

        // When
        double radius = point.getRadius();

        // Then
        assertEquals(5.0, radius);
    }

    @Test
    void testScalarMultiply() {
        // Given
        Point point = new Point(2, 3);
        double scalar = 2.0;

        // When
        Point result = point.scalarMultiply(scalar);

        // Then
        assertEquals(new Point(4, 6), result);
    }
}
