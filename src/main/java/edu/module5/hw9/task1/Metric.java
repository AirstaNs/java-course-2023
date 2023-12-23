package edu.module5.hw9.task1;

import java.util.concurrent.atomic.DoubleAdder;
import java.util.concurrent.atomic.LongAdder;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Metric {
    private final DoubleAdder sum = new DoubleAdder();
    private final LongAdder count = new LongAdder();
    private double min = Double.POSITIVE_INFINITY;
    private double max = Double.NEGATIVE_INFINITY;
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    public void add(double value) {
        lock.writeLock().lock();
        try {
            sum.add(value);
            count.increment();
            min = Math.min(min, value);
            max = Math.max(max, value);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public double getAverage() {
        long countValue = count.sum();
        return countValue > 0 ? sum.sum() / countValue : 0.0;
    }

    public double getSum() {
        return sum.doubleValue();
    }

    public double getMin() {
        lock.readLock().lock();
        try {
            return min;
        } finally {
            lock.readLock().unlock();
        }
    }

    public double getMax() {
        lock.readLock().lock();
        try {
            return max;
        } finally {
            lock.readLock().unlock();
        }
    }
}
