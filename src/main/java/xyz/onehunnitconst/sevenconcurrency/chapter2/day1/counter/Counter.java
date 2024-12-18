package xyz.onehunnitconst.sevenconcurrency.chapter2.day1.counter;

public class Counter {
    private int count = 0;

    public synchronized void increment() {
        count++;
    }

    public int getCount() {
        return count;
    }
}
