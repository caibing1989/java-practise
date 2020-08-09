package threadPool.threadMode;

/**
 * @Description:
 * @Author: mtdp
 * @Date: 2020-07-08
 */

public class MyThread extends Thread {
    private int i;
    private int ticket = 10;

    private MyThread(String s) {
        super(s);
    }

    @Override
    public void run() {
        for (; i < 20; i++) {
            if (ticket > 0) {
                System.out.println(this.getName() + " 卖票，" + ticket--);
            } else {
                System.out.println(this.getName() + " 票已经卖完了");
            }
        }
    }

    public static void main(String[] args) {
        MyThread myThread1 = new MyThread("thread1");
        MyThread myThread2 = new MyThread("thread2");
        MyThread myThread3 = new MyThread("thread3");

        myThread1.start();
        myThread2.start();
        myThread3.start();
    }
}

/**
 * 缺点：已经继承Thread类，不能继承其它父类
 * 优点：使用简单
 *
 */
