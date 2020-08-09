package threadPool.fourThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description: 演示FixThreadPoo OOM的情况
 * @Author: mtdp
 * @Date: 2020-07-27
 */

public class FixThreadPoolOOM implements Runnable {
    private static ExecutorService executorService = Executors.newFixedThreadPool(1);

    public static void main(String[] args) {
        while (true) {
            executorService.execute(new FixThreadPoolOOM());
        }
    }

    @Override
    public void run() {
        try {
            Thread.sleep(100000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
