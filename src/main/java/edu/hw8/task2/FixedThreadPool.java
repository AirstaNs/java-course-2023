package edu.hw8.task2;

import java.util.Arrays;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;

public class FixedThreadPool implements ThreadPool, Executor {
    private final Thread[] threads;
    private final BlockingQueue<Runnable> taskQueue;
    private volatile boolean isRunning = true;

    public FixedThreadPool(int nThreads) {
        threads = new Thread[nThreads];
        taskQueue = new LinkedBlockingQueue<>();

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
        }
    }

    @Override
    public void close() {
        boolean isFinished = false;
        while (!isFinished) {
            isFinished = taskQueue.isEmpty();
        }

        isRunning = false;
        Arrays.stream(threads)
            .forEach(Thread::interrupt);
    }

    private class Worker extends Thread {
        public void run() {
            while (isRunning) {
                try {
                    Runnable task = taskQueue.take();
                    task.run();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
