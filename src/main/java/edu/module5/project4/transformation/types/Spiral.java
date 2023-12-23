package edu.module5.project4.transformation.types;

import edu.module5.project4.model.Point;

public class Spiral implements Transformation {

    @Override
    public Point apply(Point t) {
        double omega = Math.atan(t.x() / t.y());
        double r = t.getRadius();
        return new Point(
            Math.cos(omega) + Math.sin(r),
            Math.sin(omega) - Math.cos(r)
        ).scalarMultiply(1.0 / r);
    }
}
