package edu.module3.project2.renderer;

import edu.module3.project2.model.Maze;
import java.awt.Point;
import java.util.Set;

public interface Renderer {
    void render(Maze maze);

    void render(Maze maze, Set<Point> path);
}
