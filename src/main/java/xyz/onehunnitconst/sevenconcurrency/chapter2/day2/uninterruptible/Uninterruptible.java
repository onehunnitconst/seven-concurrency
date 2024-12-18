package xyz.onehunnitconst.sevenconcurrency.chapter2.day2.uninterruptible;

public class Uninterruptible {
    public static void main(String[] args) throws InterruptedException {
        final Object o1 = new Object();
        final Object o2 = new Object();

        Thread thread1 = new Thread(() -> {
            try {
                synchronized (o1) {
                    Thread.sleep(1000);
                    synchronized (o2) {}
                }
            } catch (InterruptedException e) {
                System.out.println("t1 interrupted");
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                synchronized (o2) {
                    Thread.sleep(1000);
                    synchronized (o1) {}
                }
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
