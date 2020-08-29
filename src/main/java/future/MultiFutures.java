package future;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.*;

/**
 * @Description: 演示批量提交任务时，用list批量接收结果
 * @Author: mtdp
 * @Date: 2020-08-17
 */

public class MultiFutures {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        ArrayList<Future> futureArrayList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            futureArrayList.add(executorService.submit(new CallableTask()));
        }
        for (Future future : futureArrayList) {
            try {
                System.out.println(future.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        executorService.shutdown();
    }

    static class CallableTask implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            Thread.sleep(3000);
            return new Random().nextInt();
        }
    }
}
