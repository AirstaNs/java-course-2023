package edu.module5.project4.model;

import java.util.concurrent.ThreadLocalRandom;

public record Rectangle(double x, double y, double width, double height) {

    public Point randomPoint() {
        double px = ThreadLocalRandom.current().nextDouble(0, width);
        double py = ThreadLocalRandom.current().nextDouble(0, height);
        return new Point(px, py);
    }

    public boolean contains(Point p) {
        return p.x() >= x && p.x() < width + x && p.y() >= y && p.y() < height + y;
    }
}
