package edu.module2.hw2.task1;

public record Constant(double value) implements Expr {
    public Constant {
        Expr.checkForOverflow(value);
    }

    @Override
    public double evaluate() {
        return value;
    }
}
