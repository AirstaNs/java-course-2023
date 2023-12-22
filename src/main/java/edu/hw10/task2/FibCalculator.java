package edu.hw10.task2;

public interface FibCalculator {
    @Cache(persist = true)
    Long fib(int number);
}
