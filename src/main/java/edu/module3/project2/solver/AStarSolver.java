package edu.module3.project2.solver;

import edu.module3.project2.model.Maze;
import java.awt.Point;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class AStarSolver implements MazeSolver {

    @Override
    public Set<Point> solve(Maze maze, Point start, Point end) {
        Objects.requireNonNull(maze);
        Objects.requireNonNull(start);
        Objects.requireNonNull(end);

        final Map<Point, Point> cameFrom = new HashMap<>();
        Map<Point, Double> gScore = new HashMap<>();
        Map<Point, Double> fScore = new HashMap<>();

        TreeSet<Point> openSet = new TreeSet<>(new PointComparator(fScore));

        openSet.add(start);
        gScore.put(start, 0.0);
        fScore.put(start, heuristicCostEstimate(start, end));

        while (!openSet.isEmpty()) {
            Point current = openSet.pollFirst();

            if (current.equals(end)) {
                return reconstructPath(cameFrom, current);
            }

            for (Point neighbor : maze.getNeighbors(current)) {
                double tentativeGScore = gScore.getOrDefault(current, Double.MAX_VALUE) + this.distBetween();
                if (tentativeGScore < gScore.getOrDefault(neighbor, Double.MAX_VALUE)) {
                    cameFrom.put(neighbor, current);
                    gScore.put(neighbor, tentativeGScore);
                    fScore.put(neighbor, tentativeGScore + heuristicCostEstimate(neighbor, end));
                    openSet.add(neighbor);
                }
            }
        }

        return Collections.emptySet();
    }

    private double heuristicCostEstimate(Point start, Point end) {
        return Math.abs(start.x - end.x) + Math.abs(start.y - end.y);
    }

    private double distBetween() {
        return 1;
    }

    private Set<Point> reconstructPath(Map<Point, Point> from, Point current) {
        Set<Point> path = new LinkedHashSet<>();
        Point currentStep = current;
        while (currentStep != null) {
            path.add(currentStep);
            currentStep = from.get(currentStep);
        }
        return path;
    }

    private record PointComparator(Map<Point, Double> fScore) implements Comparator<Point> {
        @Override
        public int compare(Point p1, Point p2) {
            double f1 = fScore.getOrDefault(p1, Double.MAX_VALUE);
            double f2 = fScore.getOrDefault(p2, Double.MAX_VALUE);

            int result = Double.compare(f1, f2);
            if (result == 0) {
                result = comparePointsDefault(p1, p2);
            }
            return result;
        }

        private int comparePointsDefault(Point p1, Point p2) {
            if (p1.x != p2.x) {
                return Integer.compare(p1.x, p2.x);
            } else {
                return Integer.compare(p1.y, p2.y);
            }
        }
    }
}
