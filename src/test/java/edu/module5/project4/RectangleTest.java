package edu.module5.project4;

import edu.module5.project4.model.Point;
import edu.module5.project4.model.Rectangle;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RectangleTest {

    @Test
    void testContains() {
        // Given
        Rectangle rectangle = new Rectangle(0, 0, 10, 10);
        Point insidePoint = new Point(5, 5);
        Point outsidePoint = new Point(15, 15);

        // When/Then
        assertTrue(rectangle.contains(insidePoint));
        assertFalse(rectangle.contains(outsidePoint));
    }

    @Test
    void testRandomPoint() {
        // Given
        Rectangle rectangle = new Rectangle(0, 0, 10, 10);

        // When
        Point randomPoint = rectangle.randomPoint();

        // Then
        assertTrue(rectangle.contains(randomPoint));
    }
}
