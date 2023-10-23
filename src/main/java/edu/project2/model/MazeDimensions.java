package edu.project2.model;

public record MazeDimensions(int width, int height) {
    private static final int MAX_DIMENSION = 55;
    private static final int MIN_DIMENSION = 2;

    public MazeDimensions {
        this.validateDimension("Width", width);
        this.validateDimension("Height", height);
    }

    private void validateDimension(String name, int value) {
        if (value <= MIN_DIMENSION || value > MAX_DIMENSION) {
            String format = String.format("%s  must be between %d and %d.", name, MIN_DIMENSION, MAX_DIMENSION);
            throw new IllegalArgumentException(format);
        }
    }
}
