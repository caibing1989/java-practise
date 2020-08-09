package threadcoreknowledge.synchronizedLearn;

/**
 * @Description: 验证类之间的synchronized锁的可重入性
 * @Author: mtdp
 * @Date: 2020-07-14
 */

public class ClassRecursionBlock implements Runnable {
    @Override
    public void run() {
        doSomething();
    }

    public synchronized void doSomething() {
        System.out.println("我是父类方法 " + Thread.currentThread().getName());
    }
}

class TestClass extends ClassRecursionBlock {
    @Override
    public synchronized void doSomething() {
        System.out.println("我是子类方法 " + Thread.currentThread().getName());
        super.doSomething();
    }

    public static void main(String[] args) {
        TestClass classRecursionBlock = new TestClass();
        Thread thread1 = new Thread(classRecursionBlock);
        Thread thread2 = new Thread(classRecursionBlock);
        thread1.start();
        thread2.start();
    }
}
