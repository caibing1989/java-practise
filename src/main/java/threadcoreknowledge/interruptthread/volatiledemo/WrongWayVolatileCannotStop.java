package threadcoreknowledge.interruptthread.volatiledemo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @Description: 陷入线程阻塞时，该方法无法停止线程，生产者消费很快，消费者消费很慢，导致阻塞队列满了，生产者会阻塞，生产者会一直等待消费者去消费，陷入死循环
 * @Author: mtdp
 * @Date: 2020-07-12
 */

public class WrongWayVolatileCannotStop {

    static class Producer implements Runnable {
        private volatile boolean isCancel = false;

        BlockingQueue<Integer> blockingQueue;

        Producer(BlockingQueue<Integer> blockingQueue) {
            this.blockingQueue = blockingQueue;
        }

        @Override
        public void run() {
            int number = 0;

            try {
                while (number <= 10000 && !isCancel) {
                    if (number % 100 == 0) {
                        // put是生产者阻塞的地方，如遇阻塞，将在停止在这里死循环，不会去侦查isCancel标记
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
        producer.isCancel = true;
        System.out.println("producer已经将isCancel设置为true");
    }
}
