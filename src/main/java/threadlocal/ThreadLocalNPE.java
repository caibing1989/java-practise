package threadlocal;

/**
 * @Description: 演示空指针的情况
 * @Author: mtdp
 * @Date: 2020-07-27
 */

public class ThreadLocalNPE {

    private ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    private Long get() {
        return threadLocal.get();
    }

    private void set() {
        threadLocal.set(Thread.currentThread().getId());
    }

    public static void main(String[] args) {
        ThreadLocalNPE threadLocalNPE = new ThreadLocalNPE();
        threadLocalNPE.set();
        System.out.println(threadLocalNPE.get());

        new Thread(() -> {
            threadLocalNPE.set();
            System.out.println(threadLocalNPE.get());
        }).start();
    }

}
