package future;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @Description: 演示一个future的使用方法，lambda形式
 * @Author: mtdp
 * @Date: 2020-08-17
 */

public class OneFutureLambda {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        Callable callable = (() -> {
            Thread.sleep(3000);
            return new Random().nextInt();
        });

        Future future = executorService.submit(callable);

        try {
            System.out.println(future.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
    }
}
