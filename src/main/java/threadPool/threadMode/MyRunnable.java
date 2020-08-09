package threadPool.threadMode;

/**
 * @Description:
 * @Author: mtdp
 * @Date: 2020-07-09
 */

public class MyRunnable implements Runnable {
    private int i;
    private int ticket = 10;

    @Override
    public void run() {
        for (; i < 20; i++) {
            if (ticket > 0) {
                System.out.println(Thread.currentThread().getName() + " 卖票，" + ticket--);
            } else {
                System.out.println(Thread.currentThread().getName() + " 票已经卖完了");
            }
        }
    }

    public static void main(String[] args) {
        MyRunnable target = new MyRunnable();

        new Thread(target, "A").start();
        new Thread(target, "B").start();
        new Thread(target, "C").start();
    }
}

/**
 * 缺点：编程稍微复杂，需要访问线程需要使用Thead.currentThread()
 * 优点：多个线程可以共享同一个target对象，适合多个线程处理同一个资源的情况，代码和数据相对独立
 *
 */
