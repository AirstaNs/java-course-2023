package edu.module5.project4.transformation.types;

import edu.module5.project4.model.Point;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Popcorn implements Transformation {

    private static final int POPCORN_VALUE = 3;
    private double c;
    private double f;

    @Override
    public Point apply(Point t) {
        return new Point(
            t.x() + c * Math.sin(Math.tan(POPCORN_VALUE * t.y())),
            t.y() + f * Math.sin(Math.tan(POPCORN_VALUE * t.x()))
        );
    }
}
