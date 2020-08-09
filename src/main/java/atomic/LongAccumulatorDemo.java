package atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.stream.IntStream;

/**
 * @Description: 演示LongAccumulator的用法
 * @Author: mtdp
 * @Date: 2020-08-01
 */

public class LongAccumulatorDemo {

    public static void main(String[] args) {
        LongAccumulator longAccumulator = new LongAccumulator(Long::sum, 0);

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        IntStream.range(1, 10).forEach(e -> executorService.submit(() -> longAccumulator.accumulate(e)));
        executorService.shutdown();
        while (!executorService.isTerminated()) {

        }
        System.out.println(longAccumulator.getThenReset());
    }
}
