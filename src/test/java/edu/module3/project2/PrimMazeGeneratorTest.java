package edu.module3.project2;

import edu.module3.project2.generator.PrimMazeGenerator;
import edu.module3.project2.model.Cell;
import edu.module3.project2.model.Maze;
import edu.module3.project2.model.MazeDimensions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.awt.Point;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PrimMazeGeneratorTest {


    @Test
    @DisplayName("Проверка наличия стен и дорог по всему периметру лабиринта")
    void testMazeHasPath() {
        // Given:
        Random random = new Random(2321);
        PrimMazeGenerator generator = new PrimMazeGenerator(random, new MazeDimensions(10, 10));

        // When
        Maze maze = generator.generate();
        Cell[][] field = maze.getField();

            boolean foundWall = Arrays.stream(field)
                .flatMap(Arrays::stream)
                .anyMatch(Cell::isWall);

            boolean foundPassage = Arrays.stream(field)
                .flatMap(Arrays::stream)
                .anyMatch(Cell::isPassage);

            assertTrue(foundWall);
            assertTrue(foundPassage);
        }

    @Test
    @DisplayName("Проверка соответствия размеров сгенерированного лабиринта заданным размерам")
    void testMazeDimensions() {
        // Given
        MazeDimensions dimensions = new MazeDimensions(10, 10);
        PrimMazeGenerator generator = new PrimMazeGenerator(new Random(), dimensions);

        // When
        Maze maze = generator.generate();

        // Then
        assertEquals(dimensions.height(), maze.getField().length);
        assertEquals(dimensions.width(), maze.getField()[0].length);
    }

    @Test
    @DisplayName("Проверка генерации различных лабиринтов при разных сидах ")
    void testMazeRandomness() {
        // Given
        MazeDimensions dimensions = new MazeDimensions(10, 10);
        PrimMazeGenerator generator1 = new PrimMazeGenerator(new Random(3211), dimensions);
        PrimMazeGenerator generator2 = new PrimMazeGenerator(new Random(8997), dimensions);

        // When
        Maze maze1 = generator1.generate();
        Maze maze2 = generator2.generate();

        // Then
        assertFalse(Arrays.deepEquals(maze1.getField(), maze2.getField()));
    }

    @Test
    @DisplayName("Выброс исключения при использовании отрицательных размеров лабиринта")
    void testNegativeDimensionsException() {
        assertThrows(IllegalArgumentException.class, () -> new PrimMazeGenerator(new Random(), new MazeDimensions(-10, -10)));
        assertThrows(IllegalArgumentException.class, () -> new PrimMazeGenerator(new Random(), new MazeDimensions(0, 0)));
    }

    @Test
    @DisplayName("Выброс исключения при некорректной инициализации лабиринта")
    void testNullPointerExceptionException() {
        // Given
        MazeDimensions dimensions = new MazeDimensions(10, 10);
        // When & Then
        assertThrows(NullPointerException.class, () -> new PrimMazeGenerator(null, dimensions));
        assertThrows(NullPointerException.class, () -> new PrimMazeGenerator(new Random(), null));
    }

    @Test
    @DisplayName("Получение соседних проходных клеток для точки")
    void testGetNeighbors() {
        // Given
        MazeDimensions dimensions = new MazeDimensions(3, 3);
        Cell[][] field = new Cell[3][3];
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                field[i][j] = new Cell(Cell.Type.WALL, new Point(i, j));
            }
        }
        field[1][1] = new Cell(Cell.Type.PASSAGE, new Point(1, 1)); // Центральная ячейка
        field[1][0] = new Cell(Cell.Type.PASSAGE, new Point(1, 0)); // Верхняя ячейка
        Maze maze = new Maze(dimensions, field);
        Point center = new Point(1, 1);

        // When
        List<Point> neighbors = maze.getNeighbors(center);

        // Then
        assertEquals(1, neighbors.size(), "Должен быть только один сосед");
        assertTrue(neighbors.contains(new Point(1, 0)), "Верхняя ячейка является проходом");
    }
}
