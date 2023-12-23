package edu.module5.hw9.task1;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Executor;

public class StatsCollector {
    private final ConcurrentMap<String, Metric> metrics;
    private final Executor executor;

    public StatsCollector(ConcurrentMap<String, Metric> metrics, Executor executor) {
        this.metrics = metrics;
        this.executor = executor;
    }

    public CompletableFuture<Metric> push(String metricName, double[] values) {
        Metric metric = metrics.computeIfAbsent(metricName, s -> new Metric());
        return CompletableFuture.supplyAsync(() -> {
            for (double value : values) {
                metric.add(value);
            }
            return metric;
        }, executor);
    }

    public Metric getMetric(String metricName) {
        return metrics.get(metricName);
    }

    public Set<Map.Entry<String, Metric>> stats() {
        return Collections.unmodifiableSet(metrics.entrySet());
    }
}
