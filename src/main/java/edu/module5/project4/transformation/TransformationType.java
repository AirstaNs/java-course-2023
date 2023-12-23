package edu.module5.project4.transformation;

import edu.module5.project4.transformation.types.Complex;
import edu.module5.project4.transformation.types.Heart;
import edu.module5.project4.transformation.types.Polar;
import edu.module5.project4.transformation.types.Popcorn;
import edu.module5.project4.transformation.types.Spherical;
import edu.module5.project4.transformation.types.Spiral;
import edu.module5.project4.transformation.types.Transformation;

public enum TransformationType {
    Complex, Heart, Polar, Popcorn, Spherical, Spiral;

    public static Transformation create(TransformationType type, double... params) {
        return switch (type) {
            case Complex -> new Complex(params[0], params[1]);
            case Heart -> new Heart();
            case Polar -> new Polar();
            case Popcorn -> new Popcorn(params[0], params[1]);
            case Spherical -> new Spherical();
            case Spiral -> new Spiral();
            case null -> throw new IllegalArgumentException("Unsupported transformation type: " + type);
        };
    }

    public Transformation create(double... params) {
        return create(this, params);
    }
}
