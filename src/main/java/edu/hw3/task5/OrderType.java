package edu.hw3.task5;

import java.util.Comparator;

public enum OrderType {
    ASC(Comparator.naturalOrder()), DESC(Comparator.reverseOrder());

    private final Comparator<Contact> comparator;

    OrderType(Comparator<Contact> comparator) {
        this.comparator = comparator;
    }

    public Comparator<Contact> getComparator() {
        return comparator;
    }
}
