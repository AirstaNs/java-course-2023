package edu.module3.hw3.task6;

import java.util.Objects;

public record Stock(String name, Double price) implements Comparable<Stock> {
    @Override
    public int compareTo(Stock other) {
        Objects.requireNonNull(other);
        return Double.compare(other.price, this.price);
    }
}
