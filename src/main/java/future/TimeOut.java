package future;

import java.util.concurrent.*;

/**
 * @Description: 演示get的超时方法，需要超时后处理，调用future.cancel，演示cancel方法传入true和false的区别
 * 代表是否中断正在执行的任务
 * @Author: mtdp
 * @Date: 2020-08-17
 */

public class TimeOut {
    private static final Ad DEFAULT_AD = new Ad("无网络时的默认广告");
    private static final ExecutorService executorService = Executors.newFixedThreadPool(2);


    // 广告类，由有没有网络，来决定显示广告的内容
    static class Ad {
        String name;

        Ad(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Ad{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

    static class FetchAdTask implements Callable<Ad> {
        @Override
        public Ad call() throws Exception {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                System.out.println("sleep期间被中断了");
                return new Ad("被中断时候的默认广告");
            }
            return new Ad("真的广告");
        }
    }

    private void printAd() {
        Future<Ad> future = executorService.submit(new FetchAdTask());

        Ad ad;
        try {
            ad = future.get(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
            ad = new Ad("中断的默认广告");
        } catch (ExecutionException e) {
            e.printStackTrace();
            ad = new Ad("执行异常的默认广告");
        } catch (TimeoutException e) {
            e.printStackTrace();
            ad = new Ad("超时的默认广告");
            System.out.println("future.cancel = " + future.cancel(true));
        }

        executorService.shutdown();
        System.out.println(ad);
    }

    public static void main(String[] args) {
        TimeOut timeOut = new TimeOut();
        timeOut.printAd();
    }
}
