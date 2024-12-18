package xyz.onehunnitconst.sevenconcurrency.chapter2.day1.helloworld;

import xyz.onehunnitconst.sevenconcurrency.chapter2.day1.counter.Counter;

public class CountingThread extends Thread {
    Counter counter;

    CountingThread(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            counter.increment();
        }
    }
}
