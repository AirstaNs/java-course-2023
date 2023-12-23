package edu.module5.project4.model;

import lombok.Getter;

@Getter
public final class ImageFractal {
    private final Pixel[][] pixels;
    private final int height;
    private final int width;

    private ImageFractal(Pixel[][] pixels, int height, int width) {
        this.pixels = pixels;
        this.height = height;
        this.width = width;
    }

    public static ImageFractal create(int width, int height) {
        Pixel[][] pixels = new Pixel[height][width];
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                pixels[row][col] = new Pixel(0, 0, 0, 0, 0.0);
            }
        }
        return new ImageFractal(pixels, height, width);
    }

    public boolean isValidCoordinate(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    public Pixel getPixel(int x, int y) {
        if (!isValidCoordinate(x, y)) {
            throw new IllegalArgumentException("Coordinates out of bounds");
        }
        return pixels[y][x];
    }
}
