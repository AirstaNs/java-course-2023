package edu.module3.project2.renderer;

import edu.module3.project2.model.Cell;
import edu.module3.project2.model.Maze;
import java.awt.Point;
import java.util.Set;

public class MazeRenderer implements Renderer {
    ConsolePrinter consolePrinter;

    public MazeRenderer(ConsolePrinter consolePrinter) {
        this.consolePrinter = consolePrinter;
    }

    public void render(Maze maze) {
        this.renderMaze(maze, null);
    }

    public void render(Maze maze, Set<Point> path) {
        this.renderMaze(maze, path);
    }

    private void renderMaze(Maze maze, Set<Point> path) {
        consolePrinter.println();
        Cell[][] field = maze.getField();

        for (int i = 0; i < maze.getDimensions().height(); i++) {
            for (int j = 0; j < maze.getDimensions().width(); j++) {
                Cell cell = field[i][j];

                if (path != null && path.contains(cell.position())) {
                    consolePrinter.printPath();
                    continue;
                }
                consolePrinter.printCell(cell);
                consolePrinter.printEmptyCell();
            }
            consolePrinter.printResetColor();
            consolePrinter.println();
        }
    }
}
