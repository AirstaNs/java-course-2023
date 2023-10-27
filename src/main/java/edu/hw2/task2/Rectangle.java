package edu.hw2.task2;

public class Rectangle {
    private final int width;
    private final int height;

    public Rectangle(int width, int height) {
        this.validateDimensions(width, height);
        this.width = width;
        this.height = height;
    }

    private void validateDimensions(int width, int height) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Dimensions should be positive");
        }
    }

    public int getWidth() {
        return width;
    }

    protected final Rectangle setWidth(int width) {
        return new Rectangle(width, this.height);
    }

    public int getHeight() {
        return height;
    }

    protected final Rectangle setHeight(int height) {
        return new Rectangle(this.width, height);
    }

    double area() {
        return width * height;
    }
}

