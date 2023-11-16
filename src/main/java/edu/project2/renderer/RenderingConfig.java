package edu.project2.renderer;

public class RenderingConfig {
    // ANSI символы
    private final String wallColor = "\u001B[40m"; // черный фон для стен
    private final String passageColor = "\u001B[47m"; // Светло-серый фон для проходов
    private final String pathColor = "\u001B[97m"; // Яркий белый текст
    private final String resetColor = "\u001B[0m"; // Сброс цвета для пути
    private String emptyCell = "   ";
    private String pathCell = " * ";

    public RenderingConfig() {
    }

    public String getWallColor() {
        return wallColor;
    }

    public String getPassageColor() {
        return passageColor;
    }

    public String getPathColor() {
        return pathColor;
    }

    public String getResetColor() {
        return resetColor;
    }

    public String getEmptyCell() {
        return emptyCell;
    }

    public String getPath() {
        return pathCell;
    }

    public RenderingConfig setEmptyCell(String emptyCell) {
        this.emptyCell = emptyCell;
        return this;
    }

    public RenderingConfig setPath(String path) {
        this.pathCell = path;
        return this;
    }
}
