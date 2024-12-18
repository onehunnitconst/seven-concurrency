package xyz.onehunnitconst.sevenconcurrency.chapter2.day1.helloworld;

import xyz.onehunnitconst.sevenconcurrency.chapter2.day1.counter.Counter;

public class HelloWorld {
    public static void main(String[] args) throws InterruptedException {
        final Counter counter = new Counter();

        CountingThread t1 = new CountingThread(counter);
        CountingThread t2 = new CountingThread(counter);

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println(counter.getCount());
    }
}