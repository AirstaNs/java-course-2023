package edu.module3.project2;

import edu.module3.project2.model.Cell;
import edu.module3.project2.model.Maze;
import edu.module3.project2.model.MazeDimensions;
import edu.module3.project2.renderer.ConsolePrinter;
import edu.module3.project2.renderer.MazeRenderer;
import edu.module3.project2.renderer.RenderingConfig;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.awt.Point;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MazeRendererTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    @DisplayName("Проверка отрисовки лабиринта с путем")
    void testRenderMaze() {
        // Given
        RenderingConfig config = new RenderingConfig();
        ConsolePrinter printer = new ConsolePrinter(config);
        MazeRenderer renderer = new MazeRenderer(printer);

        Maze maze = createSampleMaze();
        Set<Point> path = createSamplePath();

        // When
        renderer.render(maze, path);
        String output = outContent.toString()
                                  .replaceAll("\\r", "")
                                  .replaceAll("\\n", "");
        // Then
        assertEquals(createExpectedOutput(config), output);
    }

    public String createExpectedOutput(RenderingConfig config) {
        StringBuilder expectedBuilder = new StringBuilder();
        expectedBuilder.append(config.getWallColor()).append(config.getEmptyCell());
        expectedBuilder.append(config.getWallColor()).append(config.getEmptyCell());
        expectedBuilder.append(config.getWallColor()).append(config.getEmptyCell()).append(config.getResetColor());

        expectedBuilder.append(config.getPassageColor()).append(config.getEmptyCell());
        expectedBuilder.append(config.getPassageColor()).append(config.getPathColor()).append(config.getPath()).append(config.getResetColor());
        expectedBuilder.append(config.getPassageColor()).append(config.getEmptyCell()).append(config.getResetColor());

        expectedBuilder.append(config.getWallColor()).append(config.getEmptyCell());
        expectedBuilder.append(config.getWallColor()).append(config.getEmptyCell());
        expectedBuilder.append(config.getWallColor()).append(config.getEmptyCell()).append(config.getResetColor());


       return expectedBuilder.toString();
    }

    private Maze createSampleMaze() {
        Cell[][] cells = new Cell[3][3];

        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j] = new Cell(i % 2 == 0 ? Cell.Type.WALL : Cell.Type.PASSAGE, new Point(i, j));
            }
        }

        return new Maze(new MazeDimensions(3, 3), cells);
    }

    private Set<Point> createSamplePath() {
        Set<Point> path = new HashSet<>();
        path.add(new Point(1, 1));
        return path;
    }
}
