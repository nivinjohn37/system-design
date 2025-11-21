package creational.assessment;

import java.util.concurrent.*;
import java.util.*;

public class SimpleThreadPool {

    private final BlockingQueue<Runnable> taskQueue;
    private final List<Worker> workers;
    private volatile boolean isRunning = true;

    public SimpleThreadPool(int numThreads) {
        taskQueue = new LinkedBlockingQueue<>();
        workers = new ArrayList<>();

        for (int i = 0; i < numThreads; i++) {
            Worker worker = new Worker("Worker-" + i);
            workers.add(worker);
            new Thread(worker).start();
        }
    }

    public void submit(Runnable task) {
        if (isRunning) {
            taskQueue.offer(task);
        } else {
            throw new IllegalStateException("ThreadPool is shut down");
        }
    }

    public void shutdown() {
        isRunning = false;
        for (Worker w : workers) {
            w.stopWorker();
        }
    }

    private class Worker implements Runnable {
        private final String name;
        private volatile boolean running = true;

        Worker(String name) { this.name = name; }

        @Override
        public void run() {
            while (running) {
                try {
                    Runnable task = taskQueue.take(); // wait for task
                    System.out.println(name + " executing " + task);
                    task.run();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }

        void stopWorker() {
            running = false;
        }
    }

    // ------------------ DEMO ------------------
    public static void main(String[] args) throws InterruptedException {
        SimpleThreadPool pool = new SimpleThreadPool(3);

        for (int i = 1; i <= 8; i++) {
            int taskId = i;
            pool.submit(() -> {
                System.out.println("Task " + taskId + " running on " + Thread.currentThread().getName());
                try { Thread.sleep(1000); } catch (InterruptedException ignored) {}
            });
        }

        Thread.sleep(5000);
        pool.shutdown();
    }
}
