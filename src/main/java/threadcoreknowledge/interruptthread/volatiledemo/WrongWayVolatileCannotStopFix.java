package threadcoreknowledge.interruptthread.volatiledemo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @Description:
 * @Author: mtdp
 * @Date: 2020-07-12
 */

public class WrongWayVolatileCannotStopFix {

    static class Producer implements Runnable {
        BlockingQueue<Integer> blockingQueue;

        Producer(BlockingQueue<Integer> blockingQueue) {
            this.blockingQueue = blockingQueue;
        }

        @Override
        public void run() {
            int number = 0;

            try {
                // isInterrupted来中断线程
                while (number <= 10000 && !Thread.currentThread().isInterrupted()) {
                    if (number % 100 == 0) {
                        blockingQueue.put(number);
                        System.out.println(number + "是100的倍数，被放入队列");
                    }
                    // 让生产速度远大于消费速度
//                    Thread.sleep(10);
                    number++;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("生产完毕");
            }

        }
    }

    static class Consumer {
        BlockingQueue<Integer> blockingQueue;

        Consumer(BlockingQueue<Integer> blockingQueue) {
            this.blockingQueue = blockingQueue;
        }

        boolean needMore() {
            return !(Math.random() > 0.9);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(20);
        Producer producer = new Producer(blockingQueue);

        Thread thread = new Thread(producer);
        thread.start();
        Thread.sleep(5000);

        Consumer consumer = new Consumer(blockingQueue);
        while (consumer.needMore()) {
            System.out.println(consumer.blockingQueue.take() + "被消费");
            Thread.sleep(50);
        }
        System.out.println("已经不需要更多消费了");

        // 不需要更多消费了，producer关闭生产
        thread.interrupt();
    }
}
