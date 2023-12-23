package edu.module3.hw3.task5;

import java.util.Objects;

public record Contact(String name, String surname) implements Comparable<Contact> {

    public Contact {
        Objects.requireNonNull(name);
    }

    // Если одна из фамилий null или они равны, то сравниваем по имени, иначе сравниваем по фамилии
    @Override
    public int compareTo(Contact o) {
        Objects.requireNonNull(o);

        boolean isNull = surname == null || o.surname == null;
        if (isNull || surname.equals(o.surname)) {
            return name.compareTo(o.name);
        } else {
            return surname.compareTo(o.surname);
        }
    }
}
