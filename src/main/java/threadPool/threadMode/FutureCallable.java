package threadPool.threadMode;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Description:
 * @Author: mtdp
 * @Date: 2020-07-09
 */

public class FutureCallable implements Callable<Integer> {
    private int i;
    private int ticket = 10;

    @Override
    public Integer call() {
        for (; i < 20; i++) {
            if (ticket > 0) {
                System.out.println(Thread.currentThread().getName() + " 卖票，" + ticket--);
            } else {
                System.out.println(Thread.currentThread().getName() + " 票已经卖完了");
            }
        }

        return ticket;
    }

    public static void main(String[] args) {
        FutureCallable futureCallable = new FutureCallable();
        FutureTask<Integer> task = new FutureTask<>(futureCallable);

        new Thread(task, "A").start();

        try {
            Integer result = task.get();
            System.out.println("result: " + result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
