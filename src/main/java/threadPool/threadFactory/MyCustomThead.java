package threadPool.threadFactory;


import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description:
 * @Author: mtdp
 * @Date: 2020-07-07
 */

public class MyCustomThead extends Thread {
    private AtomicInteger atomicInteger;
    private Runnable target;

    public MyCustomThead(AtomicInteger atomicInteger, Runnable runnable) {
        this.target = runnable;
        this.atomicInteger = atomicInteger;
    }

    @Override
    public void run() {
        try {
            target.run();
        } finally {
            System.out.println(this.getName() + " test " + atomicInteger.getAndDecrement());
        }
    }
}