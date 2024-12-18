package xyz.onehunnitconst.sevenconcurrency.chapter2.day2.philosopher;

import xyz.onehunnitconst.sevenconcurrency.chapter2.day1.philosophers.Chopstick;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Philosopher extends Thread {
    private final ReentrantLock left;
    private final ReentrantLock right;
    private final Random random;

    public Philosopher(ReentrantLock left, ReentrantLock right) {
        this.left = left;
        this.right = right;
        random = new Random();
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(random.nextInt(1000));
                left.lock();
                try {
                    if (right.tryLock(1000, TimeUnit.MILLISECONDS)) { // 잠금장치를 얻는 데 실패하면 타임아
                        // 오른쪽 젓가락 들어올리기
                        try {
                            Thread.sleep(random.nextInt(1000)); // 잠시 먹기
                        } finally {
                            right.unlock();
                        }
                    } else {
                        // 오른쪽 젓가락을 들어올릴 수 없음
                        // 포기하고 생각하는 상태로 돌아간다
                    }
                } finally {
                    left.unlock();
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
