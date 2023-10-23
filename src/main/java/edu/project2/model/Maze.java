package edu.project2.model;

import java.awt.Point;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Maze {
    private final MazeDimensions dimensions;

    private final Cell[][] field;

    public Maze(MazeDimensions dimensions, Cell[][] field) {
        this.dimensions = dimensions;
        this.field = field;
    }

    public MazeDimensions getDimensions() {
        return dimensions;
    }

    public Cell[][] getField() {
        return field;
    }

    public List<Point> getNeighbors(Point coordinate) {
        List<Point> potentialNeighbors = Arrays.asList(
            new Point(coordinate.x - 1, coordinate.y),
            new Point(coordinate.x + 1, coordinate.y),
            new Point(coordinate.x, coordinate.y - 1),
            new Point(coordinate.x, coordinate.y + 1)
        );

        return potentialNeighbors.stream()
            .filter(this::isValidCoordinate)
            .collect(Collectors.toList());
    }

    private boolean isValidCoordinate(Point point) {
        int height = dimensions.height();
        int width = dimensions.width();
        int x = point.x;
        int y = point.y;

        return x >= 0 && x < height
               && y >= 0 && y < width
               && field[x][y].isPassage();
    }
}
