package threadcoreknowledge.synchronizedLearn;

/**
 * @Description:
 * @Author: mtdp
 * @Date: 2020-07-13
 */

public class ObjectMethodBlock implements Runnable {

    public static void main(String[] args) throws InterruptedException {
        ObjectMethodBlock objectMethodBlock = new ObjectMethodBlock();
        Thread thread1 = new Thread(objectMethodBlock);
        Thread thread2 = new Thread(objectMethodBlock);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println("已结束");
    }

    @Override
    public void run() {
        method();
    }

    private synchronized void method() {
        System.out.println("我叫对象锁的方法实现 " + Thread.currentThread().getName());
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "运行结束");
    }
}
