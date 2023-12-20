package edu.project4.transformation.types;

import edu.project4.model.Point;
import lombok.Data;

@Data
public class Complex implements Transformation {

    private final Point point;

    public Complex(double x, double y) {
        this.point = new Point(x, y);
    }

    @Override
    public Point apply(Point p) {
        double realPart = point.x() * p.x() - point.y() * p.y();
        double imaginaryPart = point.x() * p.y() + point.y() * p.x();
        return new Point(realPart, imaginaryPart);
    }
}
