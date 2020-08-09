package threadPool.fourThreadPool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Description: super(var1, 2147483647, 0L, TimeUnit.NANOSECONDS, new ScheduledThreadPoolExecutor.DelayedWorkQueue())
 * @Author: mtdp
 * @Date: 2020-07-10
 */

public class ScheduledPool {
    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);

        for (int i = 0; i < 10; i++) {
            scheduledExecutorService.scheduleAtFixedRate(new TestRun(i), 30, 1, TimeUnit.MILLISECONDS);
        }
//        scheduledExecutorService.shutdown();
    }

    private static class TestRun implements Runnable {
        Integer cycle;

        TestRun(Integer cycle) {
            this.cycle = cycle;
        }

        @Override
        public void run() {
            System.out.println("testRun: " + cycle + " " + Thread.currentThread().getName());
        }
    }
}
