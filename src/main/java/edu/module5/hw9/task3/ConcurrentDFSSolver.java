package edu.module5.hw9.task3;

import edu.module3.project2.model.Maze;
import edu.module3.project2.solver.MazeSolver;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

public class ConcurrentDFSSolver implements MazeSolver {
    private final ForkJoinPool forkJoinPool;
    private final Map<Point, Point> prev;
    private volatile boolean reachedEnd;

    public ConcurrentDFSSolver() {
        forkJoinPool = ForkJoinPool.commonPool();
        prev = new ConcurrentHashMap<>();
    }

    @Override
    public Set<Point> solve(Maze maze, Point start, Point end) {
        Objects.requireNonNull(maze);
        Objects.requireNonNull(start);
        Objects.requireNonNull(end);

        Set<Point> visited = Collections.newSetFromMap(new ConcurrentHashMap<>());
        reachedEnd = false;

        forkJoinPool.invoke(new DFSRecursiveTask(maze, start, end, visited));

        if (reachedEnd) {
            return reconstructPath(end);
        } else {
            return Collections.emptySet();
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


    private class DFSRecursiveTask extends RecursiveAction {
        private final Maze maze;
        private final Point current;
        private final Point end;
        private final Set<Point> visited;

        DFSRecursiveTask(Maze maze, Point current, Point end, Set<Point> visited) {
            this.maze = maze;
            this.current = current;
            this.end = end;
            this.visited = visited;
        }

        @Override
        protected void compute() {
            if (reachedEnd || visited.contains(current)) {
                return;
            }

            visited.add(current);
            if (current.equals(end)) {
                reachedEnd = true;
            }

            List<DFSRecursiveTask> tasks = new ArrayList<>();

            for (Point neighbor : maze.getNeighbors(current)) {
                if (!visited.contains(neighbor)) {
                    prev.put(neighbor, current);
                    DFSRecursiveTask task = new DFSRecursiveTask(maze, neighbor, end, visited);
                    tasks.add(task);
                    task.fork();
                    if (reachedEnd) {
                        break;
                    }
                }
            }

            tasks.forEach(ForkJoinTask::join);
        }
    }
}
