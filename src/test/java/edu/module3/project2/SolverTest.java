package edu.module3.project2;

import edu.module5.hw9.task3.ConcurrentDFSSolver;
import edu.module3.project2.model.Cell;
import edu.module3.project2.model.Maze;
import edu.module3.project2.model.MazeDimensions;
import edu.module3.project2.solver.AStarSolver;
import edu.module3.project2.solver.BFSSolver;
import edu.module3.project2.solver.DFSSolver;
import edu.module3.project2.solver.MazeSolver;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import java.awt.Point;
import java.util.Set;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SolverTest {

    static Stream<MazeSolver> mazeSolverProvider() {
        return Stream.of(
            new BFSSolver(),
            new DFSSolver(),
            new AStarSolver(),
            new ConcurrentDFSSolver()
        );
    }

    @ParameterizedTest
    @MethodSource("mazeSolverProvider")
    @DisplayName("Поиск пути в лабиринте, где существует решение")
    void testSolveWithPath(MazeSolver solver) {
        // Given
        Maze maze = createMazeWithAPath();
        Point start = new Point(0, 0);
        Point end = new Point(2, 2);

        // When
        Set<Point> path = solver.solve(maze, start, end);

        // Then
        assertFalse(path.isEmpty());
        assertTrue(path.contains(start));
        assertTrue(path.contains(end));
    }

    @ParameterizedTest
    @MethodSource("mazeSolverProvider")
    @DisplayName("Поиск пути в лабиринте, где нет решения")
    void testSolveWithNoPath(MazeSolver solver) {
        // Given
        Maze maze = createMazeWithNoPath();
        Point start = new Point(0, 0);
        Point end = new Point(2, 2);

        // When
        Set<Point> path = solver.solve(maze, start, end);

        // Then
        assertTrue(path.isEmpty());
    }

    @ParameterizedTest
    @MethodSource("mazeSolverProvider")
    @DisplayName("Поиск пути в лабиринте, когда начальная и конечная точки совпадают")
    void testSolveStartEqualsEnd(MazeSolver solver) {
        // Given
        Maze maze = createSimpleMaze();
        Point startEnd = new Point(1, 1);

        // When
        Set<Point> path = solver.solve(maze, startEnd, startEnd);

        // Then
        assertEquals(1, path.size());
        assertTrue(path.contains(startEnd));
    }

    @ParameterizedTest
    @MethodSource("mazeSolverProvider")
    void testSolverWithNullParameters(MazeSolver solver) {
        Maze maze = createMazeWithAPath();
        Point start = new Point(0, 0);
        Point end = new Point(2, 2);

        assertThrows(NullPointerException.class, () -> solver.solve(null, start, end));
        assertThrows(NullPointerException.class, () -> solver.solve(maze, null, end));
        assertThrows(NullPointerException.class, () -> solver.solve(maze, start, null));
        assertThrows(NullPointerException.class, () -> solver.solve(null, null, null));
    }



    private Maze createMazeWithAPath() {
        Cell[][] field = {
            {
                new Cell(Cell.Type.PASSAGE, new Point(0, 0)),
                new Cell(Cell.Type.WALL, new Point(0, 1)),
                new Cell(Cell.Type.PASSAGE, new Point(0, 2))
            },
            {
                new Cell(Cell.Type.PASSAGE, new Point(1, 0)),
                new Cell(Cell.Type.WALL, new Point(1, 1)),
                new Cell(Cell.Type.PASSAGE, new Point(1, 2))
            },
            {new Cell(Cell.Type.PASSAGE, new Point(2, 0)),
                new Cell(Cell.Type.PASSAGE, new Point(2, 1)),
                new Cell(Cell.Type.PASSAGE, new Point(2, 2))
            }
        };
        return new Maze(new MazeDimensions(3, 3), field);
    }
    private Maze createMazeWithNoPath() {
        Cell[][] field = {
            {
                new Cell(Cell.Type.PASSAGE, new Point(0, 0)),
                new Cell(Cell.Type.WALL, new Point(0, 1)),
                new Cell(Cell.Type.PASSAGE, new Point(0, 2))
            },
            {new Cell(Cell.Type.WALL, new Point(1, 0)),
                new Cell(Cell.Type.WALL, new Point(1, 1)),
                new Cell(Cell.Type.WALL, new Point(1, 2))
            },
            {new Cell(Cell.Type.PASSAGE, new Point(2, 0)),
                new Cell(Cell.Type.PASSAGE, new Point(2, 1)),
                new Cell(Cell.Type.PASSAGE, new Point(2, 2))
            }
        };
        return new Maze(new MazeDimensions(3, 3), field);
    }

    private Maze createSimpleMaze() {
        Cell[][] field = {
            {
                new Cell(Cell.Type.PASSAGE, new Point(0, 0)),
                new Cell(Cell.Type.PASSAGE, new Point(0, 1)),
                new Cell(Cell.Type.PASSAGE, new Point(0, 2))
            },
            {new Cell(Cell.Type.PASSAGE, new Point(1, 0)),
                new Cell(Cell.Type.PASSAGE, new Point(1, 1)),
                new Cell(Cell.Type.PASSAGE, new Point(1, 2))
            },
            {new Cell(Cell.Type.PASSAGE, new Point(2, 0)),
                new Cell(Cell.Type.PASSAGE, new Point(2, 1)),
                new Cell(Cell.Type.PASSAGE, new Point(2, 2))
            }
        };
        return new Maze(new MazeDimensions(3, 3), field);
    }
}
