package threadcoreknowledge.synchronizedLearn;

/**
 * @Description:
 * @Author: mtdp
 * @Date: 2020-07-12
 */

public class ObjectCodeBlock implements Runnable {
    // 也可以使用该lock
    Object lock = new Object();

    @Override
    public void run() {
        synchronized (this) {
            System.out.println("我是对象锁的代码块方式，我叫" + Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "运行结束");
        }
    }

    public static void main(String[] args) {
        ObjectCodeBlock objectCodeBlock = new ObjectCodeBlock();
        Thread thread1 = new Thread(objectCodeBlock);
        Thread thread2 = new Thread(objectCodeBlock);
        thread1.start();
        thread2.start();
        while (thread1.isAlive() || thread2.isAlive()) {
        }
        System.out.println("运行结束");
    }
}
