package edu.module5.hw8.task2;

import java.util.Arrays;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;

public class FixedThreadPool implements ThreadPool, Executor {
    private final Thread[] threads;
    private final BlockingQueue<Runnable> taskQueue;
    private final CountDownLatch latch;
    private volatile boolean isRunning = true;

    public FixedThreadPool(int nThreads) {
        threads = new Thread[nThreads];
        taskQueue = new LinkedBlockingQueue<>();
        latch = new CountDownLatch(nThreads);

        for (int i = 0; i < nThreads; i++) {
            threads[i] = new Worker();
            threads[i].start();
        }
    }

    @Override
    public void start() {
        for (Thread thread : threads) {
            if (!thread.isAlive()) {
                thread.start();
            }
        }
    }

    @Override
    public void execute(Runnable runnable) {
        if (isRunning) {
            taskQueue.add(runnable);
        } else {
            throw new RejectedExecutionException();
        }
    }

    @Override
    public void close() {
        final int timeout = 4;
        isRunning = false;
        if (!taskQueue.isEmpty()) {
            try {
                if (!latch.await(timeout, TimeUnit.SECONDS)) {
                    taskQueue.clear();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        // прерывает ожидание потоков, если нет задач в очереди или истек timeout
        Arrays.stream(threads).forEach(Thread::interrupt);
    }

    private final class Worker extends Thread {
        public void run() {
            try {
                while (isRunning || !taskQueue.isEmpty()) {
                    Runnable task = taskQueue.take();
                    task.run();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                latch.countDown();
            }
        }
    }
}
