package edu.hw9.task1;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.DoubleAdder;
import java.util.concurrent.atomic.LongAdder;

public class Metric {
    private final DoubleAdder sum = new DoubleAdder();
    private final LongAdder count = new LongAdder();
    private final AtomicReference<Double> min = new AtomicReference<>(Double.POSITIVE_INFINITY);
    private final AtomicReference<Double> max = new AtomicReference<>(Double.NEGATIVE_INFINITY);

    public void add(double value) {
        sum.add(value);
        count.increment();
        min.getAndUpdate(currentMin -> Math.min(currentMin, value));
        max.getAndUpdate(currentMax -> Math.max(currentMax, value));
    }

    public double getAverage() {
        long countValue = count.sum();
        return countValue > 0 ? sum.sum() / countValue : 0.0;
    }

    public double getSum() {
        return sum.doubleValue();
    }

    public double getMin() {
        return min.get();
    }

    public double getMax() {
        return max.get();
    }
}
