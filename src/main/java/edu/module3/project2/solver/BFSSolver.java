package edu.module3.project2.solver;

import edu.module3.project2.model.Maze;
import java.awt.Point;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;

public class BFSSolver implements MazeSolver {
    @Override
    public Set<Point> solve(Maze maze, Point start, Point end) {
        Objects.requireNonNull(maze);
        Objects.requireNonNull(start);
        Objects.requireNonNull(end);

        Map<Point, Point> prev = new HashMap<>();
        Queue<Point> queue = new LinkedList<>();
        Set<Point> visited = new HashSet<>();

        queue.add(start);
        visited.add(start);
        prev.put(start, null);

        while (!queue.isEmpty()) {
            Point current = queue.poll();

            if (current.equals(end)) {
                return reconstructPath(prev, end);
            }

            for (Point neighbor : maze.getNeighbors(current)) {
                if (!visited.contains(neighbor)) {
                    queue.add(neighbor);
                    visited.add(neighbor);
                    prev.put(neighbor, current);
                }
            }
        }

        return Collections.emptySet();
    }

    private Set<Point> reconstructPath(Map<Point, Point> prev, Point end) {
        Set<Point> path = new LinkedHashSet<>();
        for (Point at = end; at != null; at = prev.get(at)) {
            path.add(at);
        }
        return path;
    }
}
