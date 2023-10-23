package edu.project2.renderer;

import edu.project2.model.Maze;
import java.awt.Point;
import java.util.Set;

public interface Renderer {
    void render(Maze maze);

    void render(Maze maze, Set<Point> path);
}
