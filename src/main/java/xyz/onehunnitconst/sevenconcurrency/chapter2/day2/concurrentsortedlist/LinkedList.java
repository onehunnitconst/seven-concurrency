package xyz.onehunnitconst.sevenconcurrency.chapter2.day2.concurrentsortedlist;

import java.util.Random;

public class LinkedList {
    public static void main(String[] args) throws InterruptedException {
        final ConcurrentSortedList list = new ConcurrentSortedList();
        final Random random = new Random();

        final Thread testThread1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                list.insert(random.nextInt());
            }
        });

        final Thread testThread2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                list.insert(random.nextInt());
            }
        });

        final Thread countingThread = new Thread(() -> {
            while (!Thread.interrupted()) {
                System.out.print("\r" + list.size());
                System.out.flush();
            }
        });

        testThread1.start();
        testThread2.start();
        countingThread.start();

        testThread1.join();
        testThread2.join();
        countingThread.interrupt();

        System.out.println("\r" + list.size());

        if (list.size() != 20000)
            System.out.println("*** Wrong size!");

        if (!list.isSorted())
            System.out.println("*** Not sorted!");
    }
}
