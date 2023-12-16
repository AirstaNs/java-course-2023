package edu.hw10.task2;

public class FibCalculatorImpl implements FibCalculator {
    @Override
    public long fib(int number) {
        long result;
        if (number <= 1) {
            result = number;
        } else {
            result = fib(number - 1) + fib(number - 2);
        }

        return result;
    }
}

