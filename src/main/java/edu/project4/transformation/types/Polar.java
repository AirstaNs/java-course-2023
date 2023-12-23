package edu.project4.transformation.types;

import edu.project4.model.Point;

public class Polar implements Transformation {

    @Override
    public Point apply(Point t) {
        double omega = Math.atan(t.x() / t.y());
        double r = t.getRadius();
        return new Point(omega / Math.PI, r - 1);
    }
}
