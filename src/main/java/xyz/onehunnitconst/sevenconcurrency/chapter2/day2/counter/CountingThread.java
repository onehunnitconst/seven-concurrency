package xyz.onehunnitconst.sevenconcurrency.chapter2.day2.counter;

import java.util.concurrent.atomic.AtomicInteger;

public class CountingThread extends Thread {
    private final AtomicInteger counter;

    public CountingThread(AtomicInteger counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int x = 0; x < 10000; ++x)
            counter.incrementAndGet();
    }
}
