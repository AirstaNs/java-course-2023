package edu.hw3.task5;

import org.jetbrains.annotations.NotNull;

public record Contact(String name, String surname) implements Comparable<Contact> {

    public Contact(@NotNull String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    // Если одна из фамилий null или они равны, то сравниваем по имени, иначе сравниваем по фамилии
    @Override
    public int compareTo(@NotNull Contact o) {
        boolean isNull = surname == null || o.surname == null;
        if (isNull || surname.equals(o.surname)) {
            return name.compareTo(o.name);
        } else {
            return surname.compareTo(o.surname);
        }
    }
}
