package edu.module6.hw10.task2;

public class FibCalculatorImpl implements FibCalculator {
    @Override
    public Long fib(int number) {
        if (number <= 1) {
            return (long) number;
        } else {
            return fib(number - 1) + fib(number - 2);
        }
    }
}
