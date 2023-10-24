package edu.hw3.task6;

import org.jetbrains.annotations.NotNull;

public record Stock(@NotNull String name, @NotNull Double price) implements Comparable<Stock> {
    @Override
    public int compareTo(@NotNull Stock other) {
        return Double.compare(other.price, this.price);
    }
}
