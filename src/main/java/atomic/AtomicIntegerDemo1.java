package atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description: 演示AtomicInteger的基本用法，对比非原子类线程安全问题，使用原子类后，不需要加锁，也可以保证线程安全
 * @Author: mtdp
 * @Date: 2020-08-01
 */

public class AtomicIntegerDemo1 implements Runnable {
    private static AtomicInteger atomicInteger = new AtomicInteger();

    private void incrementAtomic() {
//        atomicInteger.getAndIncrement();
        atomicInteger.getAndAdd(10);
//        atomicInteger.getAndDecrement()
    }

    // 加上synchronized就安全了
    private static volatile int basicCount = 0;

    private void incrementBasic() {
        basicCount++;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            incrementAtomic();
            incrementBasic();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        AtomicIntegerDemo1 atomicIntegerDemo1 = new AtomicIntegerDemo1();
        Thread thread1 = new Thread(atomicIntegerDemo1);
        Thread thread2 = new Thread(atomicIntegerDemo1);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println("atomicInteger: " + atomicInteger.get());
        System.out.println("basicCount: " + basicCount);
    }
}
