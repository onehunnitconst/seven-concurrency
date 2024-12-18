package xyz.onehunnitconst.sevenconcurrency.chapter2.day2.uninterruptible;

import java.util.concurrent.locks.ReentrantLock;

public class Interruptible {
    public static void main(String[] args) throws InterruptedException {
        final ReentrantLock l1 = new ReentrantLock();
        final ReentrantLock l2 = new ReentrantLock();

        Thread thread1 = new Thread(() -> {
            try {
                l1.lockInterruptibly();
                Thread.sleep(1000);
                l2.lockInterruptibly();
            } catch (InterruptedException e) {
                System.out.println("t1 interrupted");
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                l2.lockInterruptibly();
                Thread.sleep(1000);
                l1.lockInterruptibly();
            } catch (InterruptedException e) {
                System.out.println("t2 interrupted");
            }
        });

        thread1.start();
        thread2.start();

        Thread.sleep(2000);

        thread1.interrupt();
        thread2.interrupt();

        thread1.join();
        thread2.join();
    }
}
