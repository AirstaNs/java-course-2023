package edu.module3.hw3.task5;

import java.util.List;
import java.util.Objects;

public class Task5 {
    private Task5() {
    }

    public static List<Contact> processContacts(List<String> strings, OrderType order) {
        Objects.requireNonNull(order);

        if (Objects.isNull(strings) || strings.isEmpty()) {
            return List.of();
        }
        return strings.stream()
            .map(Task5::parse)
            .sorted(order.getComparator())
            .toList();

    }

    public static Contact parse(String input) {
        String[] split = input.split("\\s+");
        String name = split[0];
        String surname = null;
        if (split.length > 1) {
            surname = split[1];
        }
        return new Contact(name, surname);
    }
}
