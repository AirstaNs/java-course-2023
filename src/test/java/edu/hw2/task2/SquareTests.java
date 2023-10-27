package edu.hw2.task2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SquareTests {

    @Test
    @DisplayName("При создании квадрата, его стороны должны корректно устанавливаться и вычисляться площадь.")
    public void squareCreationPositive() {
        // Given
        int side = 5;

        // When
        Square square = new Square(side);

        // Then
        assertEquals(side, square.getWidth());
        assertEquals(side, square.getHeight());
        assertEquals(side * side, square.area());
    }

    @Test
    @DisplayName("Создание квадрата с отрицательной стороной должно вызывать исключение.")
    public void squareNegativeSide() {
        // Given
        int side = -5;

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> new Square(side));
    }

    @Test
    @DisplayName("Установка новой стороны должна возвращать новый квадрат с обновленной стороной.")
    public void squareSetSide() {
        // Given
        Square square = new Square(5);
        int newSide = 6;

        // When
        Square newSquare = square.setSide(newSide);

        // Then
        assertEquals(newSide, newSquare.getWidth());
        assertEquals(newSide, newSquare.getHeight());
    }

    @Test
    @DisplayName("Создание квадрата с нулевой стороной должно вызывать исключение.")
    public void squareZeroSide() {
        // Given
        int side = 0;

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> new Square(side));
    }

    @Test
    @DisplayName("Проверка, что квадрат действительно является частным случаем прямоугольника.")
    public void squareIsRectangle() {
        // Given
        Square square = new Square(5);
        // Then
        assertTrue(square instanceof Rectangle);
    }
}
