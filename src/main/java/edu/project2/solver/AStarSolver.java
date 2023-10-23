package edu.project2.solver;

import edu.project2.model.Maze;
import java.awt.Point;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Set;

public class AStarSolver implements MazeSolver {

    @Override
    public Set<Point> solve(Maze maze, Point start, Point end) {
        Objects.requireNonNull(maze);
        Objects.requireNonNull(start);
        Objects.requireNonNull(end);

        final Map<Point, Point> cameFrom = new HashMap<>();
        Map<Point, Double> gScore = new HashMap<>();
        Map<Point, Double> fScore = new HashMap<>();
        PriorityQueue<Point> openSet = new PriorityQueue<>(Comparator.comparingDouble(fScore::get));

        openSet.add(start);
        gScore.put(start, 0.0);
        fScore.put(start, heuristicCostEstimate(start, end));

        while (!openSet.isEmpty()) {
            Point current = openSet.poll();

            if (current.equals(end)) {
                return reconstructPath(cameFrom, current);
            }

            for (Point neighbor : maze.getNeighbors(current)) {
                double tentativeGScore = gScore.getOrDefault(current, Double.MAX_VALUE) + this.distBetween();
                if (tentativeGScore < gScore.getOrDefault(neighbor, Double.MAX_VALUE)) {
                    cameFrom.put(neighbor, current);
                    gScore.put(neighbor, tentativeGScore);
                    fScore.put(neighbor, tentativeGScore + heuristicCostEstimate(neighbor, end));
                    if (!openSet.contains(neighbor)) {
                        openSet.add(neighbor);
                    }
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

}
