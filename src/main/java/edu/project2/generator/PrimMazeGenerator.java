package edu.project2.generator;

import edu.project2.model.Cell;
import edu.project2.model.Maze;
import edu.project2.model.MazeDimensions;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class PrimMazeGenerator implements MazeGenerator {
    private final Random random;

    private final int height;
    private final int width;

    private final Cell[][] field;

    private final MazeDimensions dimensions;

    public PrimMazeGenerator(Random random, MazeDimensions dimensions) {
        Objects.requireNonNull(random);
        Objects.requireNonNull(dimensions);

        this.random = random;
        this.dimensions = dimensions;

        this.height = dimensions.height();
        this.width = dimensions.width();

        this.field = new Cell[height][width];
        this.initField();
    }

    @Override
    public Maze generate() {
        int x = random.nextInt(0, height / 2) * 2 + 1;
        int y = random.nextInt(0, width / 2) * 2 + 1;

        this.setPath(x, y);
        List<Point> frontier = new ArrayList<>();
        this.addCellsToFrontier(frontier, x, y);

        while (!frontier.isEmpty()) {
            int index = random.nextInt(frontier.size());
            Point point = frontier.get(index);

            x = point.x;
            y = point.y;

            this.setPath(x, y);
            frontier.remove(index);
            this.processDirections(x, y);
            this.addCellsToFrontier(frontier, x, y);
        }

        if (height % 2 == 0) {
            this.addHorizontalWall();
        }
        if (width % 2 == 0) {
            this.addVerticalWall();
        }
        return new Maze(dimensions, field);
    }

    private void initField() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                this.setWall(i, j);
            }
        }
    }

    private void addVerticalWall() {
        for (int i = 0; i < height; i++) {
            this.setWall(i, width - 1);
        }
    }

    private void addHorizontalWall() {
        for (int j = 0; j < width; j++) {
            this.setWall(height - 1, j);
        }
    }

    private void addCellsToFrontier(List<Point> frontier, int x, int y) {

        if (y - 2 >= 0 && field[x][y - 2].isWall()) {
            frontier.add(new Point(x, y - 2));
        }
        if (y + 2 < width && field[x][y + 2].isWall()) {
            frontier.add(new Point(x, y + 2));
        }
        if (x - 2 >= 0 && field[x - 2][y].isWall()) {
            frontier.add(new Point(x - 2, y));
        }
        if (x + 2 < height && field[x + 2][y].isWall()) {
            frontier.add(new Point(x + 2, y));
        }
    }

    private void processDirections(int x, int y) {
        List<Direction> directions = Arrays.asList(Direction.values());
        Collections.shuffle(directions, random);

        for (Direction direction : directions) {
            if (direction.isMovePossible(x, y, field, width, height)) {
                Point newPoint = direction.move(x, y);
                setPath(newPoint.x, newPoint.y);
                return;
            }
        }
    }

    private void setPath(int x, int y) {
        field[x][y] = new Cell(Cell.Type.PASSAGE, new Point(x, y));
    }

    private void setWall(int x, int y) {
        field[x][y] = new Cell(Cell.Type.WALL, new Point(x, y));
    }
}
