package atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * @Description: 展示Atomic的性能远远小于LongAdder
 * @Author: mtdp
 * @Date: 2020-08-01
 */

public class AtomicLongDemo {

    public static void main(String[] args) {
        AtomicLong counter = new AtomicLong(0);
        LongAdder counter2 = new LongAdder();

        ExecutorService executorService = Executors.newFixedThreadPool(20);
        ExecutorService executorService2 = Executors.newFixedThreadPool(20);

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            executorService.submit(new Task(counter));
        }

        executorService.shutdown();
        while (!executorService.isTerminated()) {

        }
        long endTime = System.currentTimeMillis();
        System.out.println("AtomicLong 总共花费了" + (endTime - startTime) + "毫秒");
        System.out.println("AtomicLong counter = " + counter.get());

        System.out.println("---------------------------");

        long startTime2 = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            executorService2.submit(new Task2(counter2));
        }

        executorService2.shutdown();
        while (!executorService2.isTerminated()) {

        }
        long endTime2 = System.currentTimeMillis();
        System.out.println("LongAdder 总共花费了" + (endTime2 - startTime2) + "毫秒");
        System.out.println("LongAdder counter2 = " + counter2.sum());
    }

    private static class Task implements Runnable {
        AtomicLong counter;

        public Task(AtomicLong counter) {
            this.counter = counter;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                counter.getAndIncrement();
            }
        }
    }

    private static class Task2 implements Runnable {
        LongAdder counter;

        public Task2(LongAdder counter) {
            this.counter = counter;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                counter.increment();
            }
        }
    }
}
