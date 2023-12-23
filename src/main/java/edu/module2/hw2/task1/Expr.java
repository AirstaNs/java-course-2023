package edu.module2.hw2.task1;

public sealed interface Expr permits Addition, Constant, Exponent, Multiplication, Negate {
    double evaluate();

    static void checkForOverflow(double value) {
        if (Double.isInfinite(value) || Double.isNaN(value)) {
            throw new ArithmeticException("Double overflow or NaN");
        }
    }
}
