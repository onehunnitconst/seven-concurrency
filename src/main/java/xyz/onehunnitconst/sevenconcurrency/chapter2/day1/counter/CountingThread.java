package xyz.onehunnitconst.sevenconcurrency.chapter2.day1.counter;

public class CountingThread extends Thread {
    private final Counter counter;

    public CountingThread(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int x = 0; x < 10000; ++x)
            counter.increment();
    }
}
