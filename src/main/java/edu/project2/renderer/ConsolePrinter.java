package edu.project2.renderer;

import edu.project2.model.Cell;

@SuppressWarnings("RegexpSinglelineJava")
public class ConsolePrinter {
    private final RenderingConfig config;

    public ConsolePrinter(RenderingConfig config) {
        this.config = config;
    }

    public void printEmptyCell() {
        System.out.print(config.getEmptyCell());
    }

    public void printPath() {
        System.out.print(config.getPassageColor() + config.getPathColor());
        System.out.print(config.getPath());
        System.out.print(config.getResetColor());
    }

    public void printResetColor() {
        System.out.print(config.getResetColor());
    }

    public void printCell(Cell cell) {
        String cellColor = cell.isWall() ? config.getWallColor() : config.getPassageColor();
        System.out.print(cellColor);
    }

    public void println() {
        System.out.println();
    }
}
