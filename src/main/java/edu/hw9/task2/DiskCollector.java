package edu.hw9.task2;

import java.nio.file.Path;
import java.util.Collection;
import java.util.Objects;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.function.BiPredicate;

public class DiskCollector<T> {
    private final ConcurrentLinkedDeque<Path> matchingPaths;

    private final BiPredicate<Path, T> predicate;

    public DiskCollector(BiPredicate<Path, T> predicate) {
        Objects.requireNonNull(predicate);
        this.predicate = predicate;
        this.matchingPaths = new ConcurrentLinkedDeque<>();
    }

    public void collect(Path path, T argument) {
        if (predicate.test(path, argument)) {
            matchingPaths.add(path);
        }
    }

    public Collection<Path> getMatchingPaths() {
        return matchingPaths;
    }

    public BiPredicate<Path, T> getPredicate() {
        return predicate;
    }
}
