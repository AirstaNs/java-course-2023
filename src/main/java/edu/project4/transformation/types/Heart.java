package edu.project4.transformation.types;

import edu.project4.model.Point;

public class Heart implements Transformation {

    @Override
    public Point apply(Point t) {
        double omega = Math.atan(t.x() / t.y());
        double r = t.getTheta();

        double omegaR = omega * r;
        return new Point(Math.sin(omegaR), -Math.cos(omegaR)).scalarMultiply(r);
    }
}
