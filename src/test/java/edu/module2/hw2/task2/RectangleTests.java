package edu.module2.hw2.task2;

import java.util.Arrays;
import java.util.List;

import edu.module2.hw2.task2.Rectangle;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RectangleTests {

    private static List<Arguments> invalidDimensionsProvider() {
        return Arrays.asList(
            Arguments.of(-4, 5),
            Arguments.of(4, -5),
            Arguments.of(-4, -5),
            Arguments.of(0, 1),
            Arguments.of(1, 0),
            Arguments.of(0, 0)
        );
    }

    private static List<Arguments> rectangleDimensionsProvider() {
        return List.of(Arguments.of(4, 5));
    }

    @ParameterizedTest
    @MethodSource("rectangleDimensionsProvider")
    @DisplayName("Создание прямоугольника должно корректно устанавливать его ширину и высоту.")
    public void rectangleCreationPositive(int width, int height) {
        // When
        Rectangle rectangle = new Rectangle(width, height);

        // Then
        assertEquals(width, rectangle.getWidth());
        assertEquals(height, rectangle.getHeight());
        assertEquals(width * height, rectangle.area());
    }

    @ParameterizedTest
    @MethodSource("invalidDimensionsProvider")
    @DisplayName("Создание прямоугольника с отрицательными или нулевыми размерами должно вызывать исключение.")
    public void rectangleNegativeDimensions(int width, int height) {
        // When & Then
        assertThrows(IllegalArgumentException.class, () -> new Rectangle(width, height));
    }

    @ParameterizedTest
    @MethodSource("invalidDimensionsProvider")
    @DisplayName("Установка сторон прямоугольника, не положительных значений должна вызывать исключение.")
    public void rectangleSettersWithInvalidValues(int width, int height) {
        // Given
        Rectangle rectangle = new Rectangle(11, 12);

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> {
            rectangle.setWidth(width);
            rectangle.setHeight(height);
        });
    }

    @ParameterizedTest
    @MethodSource("rectangleDimensionsProvider")
    @DisplayName("Установка новой ширины должна возвращать новый прямоугольник с обновленной шириной.")
    public void rectangleSetWidth(int width, int height) {
        // Given
        Rectangle rectangle = new Rectangle(width, height);
        int newWidth = 6;

        // When
        Rectangle newRectangle = rectangle.setWidth(newWidth);

        // Then
        assertEquals(newWidth, newRectangle.getWidth());
        assertEquals(height, newRectangle.getHeight());
    }

    @ParameterizedTest
    @MethodSource("rectangleDimensionsProvider")
    @DisplayName("Установка новой высоты должна возвращать новый прямоугольник с обновленной высотой.")
    public void rectangleSetHeight(int width, int height) {
        // Given
        Rectangle rectangle = new Rectangle(width, height);
        int newHeight = 6;

        // When
        Rectangle newRectangle = rectangle.setHeight(newHeight);

        // Then
        assertEquals(width, newRectangle.getWidth());
        assertEquals(newHeight, newRectangle.getHeight());
    }

}
