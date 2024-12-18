package xyz.onehunnitconst.sevenconcurrency.chapter2.day2.counter;

import java.util.concurrent.atomic.AtomicInteger;

public class Counting {
    public static void main(String[] args) throws InterruptedException {
        // Atomic Variable은 개발자가 잠금장치나 블로킹을 할 필요 없음
        // 잠금장치가 걸리지 않으므로 데드락 발생 불가능
        // 논블로킹, 락프리 코드
        final AtomicInteger counter = new AtomicInteger(0);

        CountingThread t1 = new CountingThread(counter);
        CountingThread t2 = new CountingThread(counter);

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println(counter.get());
    }
}
