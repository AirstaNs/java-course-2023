package edu.module5.project4.model;

public record Point(double x, double y) {

    public double getTheta() {
        return Math.atan(x / y);
    }

    public double getRadius() {
        return Math.sqrt(this.getRadiusDouble());
    }

    public double getRadiusDouble() {
        return x * x + y * y;
    }

    public Point scalarMultiply(double a) {
        return new Point(a * x, a * y);
    }
}
