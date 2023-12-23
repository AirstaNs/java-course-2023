package edu.module2.hw2.task1;

public record Exponent(Expr base, double exponent) implements Expr {
    @Override
    public double evaluate() {
        double result = Math.pow(base.evaluate(), exponent);
        Expr.checkForOverflow(result);
        return result;
    }
}
