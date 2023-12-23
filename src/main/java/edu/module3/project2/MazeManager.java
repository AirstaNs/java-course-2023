package edu.module3.project2;

import edu.module3.project2.generator.MazeGenerator;
import edu.module3.project2.renderer.Renderer;
import edu.module3.project2.solver.MazeSolver;
import edu.module3.project2.model.Maze;
import java.awt.Point;
import java.util.Set;

public class MazeManager {

    private final MazeGenerator generator;
    private final MazeSolver solver;

    private final Renderer renderer;

    private Maze maze;

    private Set<Point> solution;

    public MazeManager(MazeGenerator generator, MazeSolver solver, Renderer renderer) {
        this.generator = generator;
        this.solver = solver;
        this.renderer = renderer;
    }

    public void generate() {
        maze = generator.generate();
    }

    public void solve(Point start, Point end) {
        Set<Point> solve = solver.solve(maze, start, end);
        if (solve == null || solve.isEmpty()) {
            throw new RuntimeException("start or end are not reachable");
        }
        this.solution = solve;
    }

    public void render(boolean withSolve) {
        if (withSolve) {
            renderer.render(maze, solution);
        } else {
            renderer.render(maze);
        }
    }
}
