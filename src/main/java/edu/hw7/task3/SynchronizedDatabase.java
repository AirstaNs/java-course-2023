package edu.hw7.task3;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Collectors;

public final class SynchronizedDatabase implements PersonDatabase {
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final Map<Integer, Person> database = new HashMap<>();
    private final Map<String, Set<Integer>> nameIndex = new HashMap<>();
    private final Map<String, Set<Integer>> addressIndex = new HashMap<>();
    private final Map<String, Set<Integer>> phoneIndex = new HashMap<>();

    @Override
    public void add(final Person person) {
        lock.writeLock().lock();
        try {
            database.put(person.id(), person);
            indexPerson(person, nameIndex, person.name());
            indexPerson(person, addressIndex, person.address());
            indexPerson(person, phoneIndex, person.phoneNumber());
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public void delete(int id) {
        lock.writeLock().lock();
        try {
            Person person = database.remove(id);
            if (person != null) {
                removeFromIndex(nameIndex, person.name(), id);
                removeFromIndex(addressIndex, person.address(), id);
                removeFromIndex(phoneIndex, person.phoneNumber(), id);
            }
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public List<Person> findByName(String name) {
        return findByAttribute(nameIndex, name);
    }

    @Override
    public List<Person> findByAddress(String address) {
        return findByAttribute(addressIndex, address);
    }

    @Override
    public List<Person> findByPhone(String phone) {
        return findByAttribute(phoneIndex, phone);
    }

    @Override
    public int getSize() {
        lock.readLock().lock();
        try {
            return database.size();
        } finally {
            lock.readLock().unlock();
        }
    }

    private void indexPerson(Person person, Map<String, Set<Integer>> index, String key) {
        index.computeIfAbsent(key, k -> new HashSet<>()).add(person.id());
    }

    private void removeFromIndex(Map<String, Set<Integer>> index, String key, int id) {
        Set<Integer> ids = index.get(key);
        if (ids != null) {
            ids.remove(id);
            if (ids.isEmpty()) {
                index.remove(key);
            }
        }
    }

    private List<Person> findByAttribute(Map<String, Set<Integer>> index, String key) {
        lock.readLock().lock();
        try {
            Set<Integer> ids = index.getOrDefault(key, Collections.emptySet());
            return ids.stream()
                .map(database::get)
                .collect(Collectors.toList());
        } finally {
            lock.readLock().unlock();
        }
    }
}
