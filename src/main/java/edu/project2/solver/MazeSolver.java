package edu.project2.solver;

import edu.project2.model.Maze;
import java.awt.Point;
import java.util.Set;

public interface MazeSolver {
    Set<Point> solve(Maze maze, Point start, Point end);
}
