package org.example.task2;

import java.util.concurrent.*;

public class FizzBuzz {

    private int n;
    private BlockingQueue<String> outputQueue = new LinkedBlockingQueue<>();
    private int currentNumber = 1;
    private final Object lock = new Object();

    public FizzBuzz(int n) {
        this.n = n;
    }

    public void start() {
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        executorService.execute(this::fizz);
        executorService.execute(this::buzz);
        executorService.execute(this::fizzbuzz);
        executorService.execute(this::number);

        executorService.shutdown();
        try {
            executorService.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void fizz() {
        try {
            while (true) {
                synchronized (lock) {
                    if (currentNumber > n) break;
                    if (currentNumber % 3 == 0 && currentNumber % 5 != 0) {
                        outputQueue.put("fizz");
                        currentNumber++;
                        lock.notifyAll();
                    } else {
                        lock.wait();
                    }
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void buzz() {
        try {
            while (true) {
                synchronized (lock) {
                    if (currentNumber > n) break;
                    if (currentNumber % 5 == 0 && currentNumber % 3 != 0) {
                        outputQueue.put("buzz");
                        currentNumber++;
                        lock.notifyAll();
                    } else {
                        lock.wait();
                    }
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void fizzbuzz() {
        try {
            while (true) {
                synchronized (lock) {
                    if (currentNumber > n) break;
                    if (currentNumber % 3 == 0 && currentNumber % 5 == 0) {
                        outputQueue.put("fizzbuzz");
                        currentNumber++;
                        lock.notifyAll();
                    } else {
                        lock.wait();
                    }
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void number() {
        try {
            while (true) {
                synchronized (lock) {
                    if (currentNumber > n && outputQueue.isEmpty()) break;
                    if (!outputQueue.isEmpty()) {
                        System.out.print(outputQueue.take() + ", ");
                        lock.notifyAll();
                    } else {
                        if (currentNumber <= n) {
                            if (currentNumber % 3 != 0 && currentNumber % 5 != 0) {
                                outputQueue.put(String.valueOf(currentNumber));
                                currentNumber++;
                            }
                            lock.notifyAll();
                        }
                    }
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
