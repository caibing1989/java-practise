package concurrentcollection;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description: 组合操作并不保证线程安全，只能保证get、put 线程安全，不能保证程序自己的操作线程安全
 * @Author: mtdp
 * @Date: 2020-08-01
 */

public class OptionsNotSafe implements Runnable {

    private static ConcurrentHashMap<String, Integer> concurrentHashMap = new ConcurrentHashMap<>();

    @Override
    public void run() {
        // 这个不能保证线程安全
//        for (int i = 0; i < 10000; i++) {
//            Integer score = concurrentHashMap.get("xiaoming");
//            Integer newScore = score + 1;
//            concurrentHashMap.put("xiaoming", newScore);
//        }

        // 这个可以保证线程安全
        for (int i = 0; i < 10000; i++) {
            while (true) {
                Integer score = concurrentHashMap.get("xiaoming");
                Integer newScore = score + 1;
                boolean flag = concurrentHashMap.replace("xiaoming", score, newScore);
                if (flag) {
                    break;
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        concurrentHashMap.put("xiaoming", 0);

        Thread thread1 = new Thread(new OptionsNotSafe());
        Thread thread2 = new Thread(new OptionsNotSafe());

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println(concurrentHashMap.get("xiaoming"));
    }


}
