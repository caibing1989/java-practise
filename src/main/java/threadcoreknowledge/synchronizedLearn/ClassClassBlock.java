package threadcoreknowledge.synchronizedLearn;

/**
 * @Description:
 * @Author: mtdp
 * @Date: 2020-07-13
 */

public class ClassClassBlock implements Runnable {

    public static void main(String[] args) throws InterruptedException {
        ClassClassBlock classStaticBlock1 = new ClassClassBlock();
        ClassClassBlock classStaticBlock2 = new ClassClassBlock();

        Thread thread1 = new Thread(classStaticBlock1);
        Thread thread2 = new Thread(classStaticBlock2);
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
        synchronized (ClassClassBlock.class) {
            System.out.println("我叫类锁的方法块实现 " + Thread.currentThread().getName());
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "运行结束");
        }
    }
}
