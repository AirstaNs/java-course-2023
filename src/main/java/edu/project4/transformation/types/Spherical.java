package edu.project4.transformation.types;

import edu.project4.model.Point;

public class Spherical implements Transformation {

    @Override
    public Point apply(Point p) {
        return p.scalarMultiply(1.0 / p.getRadiusDouble());
    }
}
