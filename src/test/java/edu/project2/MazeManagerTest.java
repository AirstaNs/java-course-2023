package edu.project2;

import edu.project2.generator.MazeGenerator;
import edu.project2.generator.PrimMazeGenerator;
import edu.project2.model.MazeDimensions;
import edu.project2.renderer.ConsolePrinter;
import edu.project2.renderer.MazeRenderer;
import edu.project2.renderer.Renderer;
import edu.project2.renderer.RenderingConfig;
import edu.project2.solver.BFSSolver;
import edu.project2.solver.MazeSolver;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.Point;
import java.util.Random;

public class MazeManagerTest {

    MazeGenerator generator;
    MazeSolver solver;
    Renderer renderer;
    MazeManager manager;

    @BeforeEach
    void setUp() {
        generator = new PrimMazeGenerator(new Random(15313122),new MazeDimensions(10,10));
        solver = new BFSSolver();
        renderer = new MazeRenderer(new ConsolePrinter(new RenderingConfig()));

        manager = new MazeManager(generator, solver, renderer);
        manager.generate();
    }

    @Test
    @DisplayName("Успешное решение лабиринта")
    void testSuccessfulSolve() {
        // Given
        Point start = new Point(1, 1);
        Point end = new Point(3, 3);
        // When & Then
        assertDoesNotThrow(() -> manager.solve(start, end));
    }

    @Test
    @DisplayName("Неудачное решение лабиринта с исключением")
    void testFailedSolve() {
        // Given
        Point start = new Point(0, 0);
        Point end = new Point(100, 100);
        // When & Then
        assertThrows(RuntimeException.class, () -> manager.solve(start, end));
    }
}
