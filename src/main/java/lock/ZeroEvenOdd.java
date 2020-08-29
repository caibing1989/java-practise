package lock;

import java.util.concurrent.Semaphore;

/**
 * @Description:
 * @Author: mtdp
 * @Date: 2020-08-22
 */

public class ZeroEvenOdd {
    private int n;

    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    private Semaphore semaphoreZero = new Semaphore(1);
    private Semaphore semaphoreEven = new Semaphore(0);
    private Semaphore semaphoreOdd = new Semaphore(0);

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero() throws InterruptedException {
        for (int i = 0; i < n; ) {
            semaphoreZero.acquire(1);
            System.out.println(0);
            i++;
            if (i % 2 == 0) {
                semaphoreEven.release(1);
            } else {
                semaphoreOdd.release(1);
            }
        }
    }

    public void even() throws InterruptedException {
        for (int i = 2; i <= n; i += 2) {
            semaphoreEven.acquire(1);
            System.out.println(i);
            semaphoreZero.release(1);
        }
    }

    public void odd() throws InterruptedException {
        for (int i = 1; i <= n; i += 2) {
            semaphoreOdd.acquire(1);
            System.out.println(i);
            semaphoreZero.release(1);
        }
    }

    public static void main(String[] args) {
        ZeroEvenOdd zeroEvenOdd = new ZeroEvenOdd(6);
        new Thread(() -> {
            try {
                zeroEvenOdd.zero();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                zeroEvenOdd.even();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                zeroEvenOdd.odd();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
