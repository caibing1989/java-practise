package future;

import java.util.concurrent.*;

/**
 * @Description: 演示get方法过程中抛出异常，for循环为了演示抛出Exception的时机：并不是一产生异常就抛出
 * 直到get方法运行的时候才会抛出
 * @Author: mtdp
 * @Date: 2020-08-17
 */

public class GetException {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        Future<Integer> future = executorService.submit(new CallableTask());

        try {
            for (int i = 0; i < 5; i++) {
                System.out.println("sleep i = " + i);
                Thread.sleep(500);
            }
            System.out.println("isDone = " + future.isDone());
            future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("InterruptedException");
        } catch (ExecutionException e) {
            e.printStackTrace();
            System.out.println("ExecutionException");
        }

        executorService.shutdown();
    }

    static class CallableTask implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            throw new IllegalArgumentException("callable throw IllegalArgumentException");
        }
    }
}
