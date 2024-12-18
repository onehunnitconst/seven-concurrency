package xyz.onehunnitconst.sevenconcurrency.chapter2.day1.philosophers;

import java.util.Random;

public class Philosopher extends Thread {
    private final Chopstick first;
    private final Chopstick second;
    private final Random random;

    public Philosopher(Chopstick left, Chopstick right) {
        if (left.getId() < right.getId()) {
            this.first = left;
            this.second = right;
        } else {
            this.first = right;
            this.second = left;
        }
        random = new Random();
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(random.nextInt(1000));
                synchronized (first) {
                    synchronized (second) {
                        Thread.sleep(random.nextInt(1000));
                    }
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
