package edu.module3.project2.generator;

import edu.module3.project2.model.Cell;
import java.awt.Point;

public enum Direction {
    UP, DOWN, LEFT, RIGHT;

    public boolean isMovePossible(int x, int y, Cell[][] field, int width, int height) {
        return switch (this) {
            case LEFT -> x - 2 >= 0 && field[x - 2][y].isPassage();
            case RIGHT -> x + 2 < height && field[x + 2][y].isPassage();
            case DOWN -> y - 2 >= 0 && field[x][y - 2].isPassage();
            case UP -> y + 2 < width && field[x][y + 2].isPassage();
        };
    }

    public Point move(int x, int y) {
        return switch (this) {
            case LEFT -> new Point(x - 1, y);
            case RIGHT -> new Point(x + 1, y);
            case DOWN -> new Point(x, y - 1);
            case UP -> new Point(x, y + 1);
        };
    }
}
