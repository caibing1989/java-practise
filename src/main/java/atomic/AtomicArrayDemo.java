package atomic;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * @Description: 演示原子数组的使用方法，例如财务中对很多元素进行修改，可以通过这个原子数组保证线程安全
 * @Author: mtdp
 * @Date: 2020-08-01
 */

public class AtomicArrayDemo {
    public static void main(String[] args) throws InterruptedException {
        AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(100);
        Decrement decrement = new Decrement(atomicIntegerArray);
        Increment increment = new Increment(atomicIntegerArray);

        Thread[] threads1 = new Thread[100];
        Thread[] threads2 = new Thread[100];

        for (int i = 0; i < 100; i++) {
            threads1[i] = new Thread(decrement);
            threads1[i].start();
            threads2[i] = new Thread(increment);
            threads2[i].start();
        }

        for (int i = 0; i < 100; i++) {
            threads1[i].join();
            threads2[i].join();
        }

        for (int i = 0; i < 100; i++) {
            if (atomicIntegerArray.get(i) != 0) {
                System.out.println("发现非0的情况");
            }
        }

        System.out.println("程序运行完毕");
    }

    static class Decrement implements Runnable {
        AtomicIntegerArray atomicIntegerArray;

        public Decrement(AtomicIntegerArray atomicIntegerArray) {
            this.atomicIntegerArray = atomicIntegerArray;
        }

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                atomicIntegerArray.getAndDecrement(i);
            }
        }
    }

    static class Increment implements Runnable {
        AtomicIntegerArray atomicIntegerArray;

        public Increment(AtomicIntegerArray atomicIntegerArray) {
            this.atomicIntegerArray = atomicIntegerArray;
        }

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                atomicIntegerArray.getAndIncrement(i);
            }
        }
    }
}
