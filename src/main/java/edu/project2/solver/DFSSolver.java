package edu.project2.solver;

import edu.project2.model.Maze;
import java.awt.Point;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class DFSSolver implements MazeSolver {

    private Set<Point> visited;
    private Map<Point, Point> prev;
    private boolean reachedEnd;

    @Override
    public Set<Point> solve(Maze maze, Point start, Point end) {
        Objects.requireNonNull(maze);
        Objects.requireNonNull(start);
        Objects.requireNonNull(end);

        visited = new HashSet<>();
        prev = new HashMap<>();
        reachedEnd = false;

        dfs(maze, start, end);

        if (reachedEnd) {
            return reconstructPath(end);
        } else {
            return Collections.emptySet();
        }
    }

    private void dfs(Maze maze, Point current, Point end) {
        if (reachedEnd || visited.contains(current)) {
            return;
        }

        visited.add(current);

        if (current.equals(end)) {
            reachedEnd = true;
        }

        if (!reachedEnd) {
            for (Point neighbor : maze.getNeighbors(current)) {
                if (!visited.contains(neighbor)) {
                    prev.put(neighbor, current);
                    dfs(maze, neighbor, end);
                    if (reachedEnd) {
                        break;
                    }
                }
            }
        }
    }

    private Set<Point> reconstructPath(Point end) {
        LinkedList<Point> path = new LinkedList<>();
        Point at = end;

        while (at != null) {
            path.addFirst(at);
            at = prev.get(at);
        }

        return new LinkedHashSet<>(path);
    }

}
