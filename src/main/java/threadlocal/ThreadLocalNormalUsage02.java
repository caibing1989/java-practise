package threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description: SimpleDateFormat不用每次都调用，只生成一次，但是这样会有线程安全问题
 * @Author: mtdp
 * @Date: 2020-07-27
 */

public class ThreadLocalNormalUsage02 {
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    private static ExecutorService executorService = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {

        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            executorService.execute(() -> {
                String date = new ThreadLocalNormalUsage02().date(finalI);
                System.out.println(date);
            });
        }
        executorService.shutdown();
    }

    public String date(int seconds) {
        // 参数的单位为毫秒，从1970.01.01 00:00:00开始
        Date date = new Date(1000 * seconds);
        return simpleDateFormat.format(date);
    }
}
