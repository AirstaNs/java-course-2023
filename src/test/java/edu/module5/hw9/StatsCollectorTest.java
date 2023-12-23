package edu.module5.hw9;

import edu.module5.hw9.task1.Metric;
import edu.module5.hw9.task1.StatsCollector;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.*;

class StatsCollectorTest {
    private StatsCollector collector;
    private ExecutorService executorService;

    @BeforeEach
    void setUp() {
        executorService = Executors.newFixedThreadPool(4);
        collector = new StatsCollector(new ConcurrentHashMap<>(), executorService);
    }

    @AfterEach
    void tearDown() throws InterruptedException {
        executorService.shutdown();
        if (!executorService.awaitTermination(3, TimeUnit.SECONDS)) {
            executorService.shutdownNow();
        }
    }

    @Test
    void testPushData() throws ExecutionException, InterruptedException {
        double[] values = {1.0, 2.0, 3.0};
        CompletableFuture<Metric> future = collector.push("testMetric", values);
        Metric metric = future.get();

        assertNotNull(metric);
        assertEquals(6.0, metric.getSum());
        assertEquals(2.0, metric.getAverage());
        assertEquals(1.0, metric.getMin());
        assertEquals(3.0, metric.getMax());
    }

    @Test
    void testGetMetric() {
        collector.push("testMetric", new double[] {1.0, 2.0, 3.0}).join();
        Metric metric = collector.getMetric("testMetric");

        assertNotNull(metric);
        assertEquals(6.0, metric.getSum());
    }

    @Test
    void testGetMetricNonExistent() {
        assertNull(collector.getMetric("nonExistentMetric"));
    }

    @Test
    void testStats() {
        collector.push("metric1", new double[] {1.0, 2.0, 3.0}).join();
        collector.push("metric2", new double[] {4.0, 5.0, 6.0}).join();

        Set<Map.Entry<String, Metric>> stats = collector.stats();
        assertNotNull(stats);
        assertTrue(stats.size() >= 2);
    }

}
