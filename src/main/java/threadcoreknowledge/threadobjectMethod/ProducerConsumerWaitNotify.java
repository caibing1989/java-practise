package threadcoreknowledge.threadobjectMethod;

import java.util.LinkedList;

/**
 * @Description: 生产者消费者模式
 * @Author: mtdp
 * @Date: 2020-07-18
 */

public class ProducerConsumerWaitNotify {
    private static final StorageQueue storageQueue = new StorageQueue(10);

    public static void main(String[] args) {
        Thread thread1 = new Thread(new Producer());
        Thread thread2 = new Thread(new Consumer());
        thread1.start();
        thread2.start();
    }


    static class StorageQueue {
        LinkedList<Integer> list;
        int maxSize;

        StorageQueue(int maxSize) {
            this.list = new LinkedList<>();
            this.maxSize = maxSize;
        }

        synchronized void take() {
            while (list.size() == 0) {
                System.out.println(Thread.currentThread().getName() + " 队列已经空了");
                try {
                    System.out.println(Thread.currentThread().getName() + " take进入等待");
                    wait();
                    System.out.println(Thread.currentThread().getName() + " 结束等待，可以继续take");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            Integer result = list.poll();
            notify();
            System.out.println(Thread.currentThread().getName() + " 成功消费" + result + "，还剩下元素个数：" + list.size());
        }

        synchronized void put(Integer e) {
            while (maxSize == list.size()) {
                System.out.println(Thread.currentThread().getName() + " 队列已经满了");
                try {
                    System.out.println(Thread.currentThread().getName() + " put进入等待");
                    wait();
                    System.out.println(Thread.currentThread().getName() + " 结束等待，可以继续put");
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }

            list.offer(e);
            System.out.println(Thread.currentThread().getName() + " 成功放入" + e + "，还剩下空间：" + (maxSize - list.size()));
            notify();
        }
    }

    static class Producer implements Runnable {
        @Override
        public void run() {
            for (int i = 1; i <= 100; i++) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                storageQueue.put(i);
            }
            System.out.println("全部生产完毕");
        }
    }

    static class Consumer implements Runnable {
        @Override
        public void run() {
            for (int i = 1; i <= 100; i++) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                storageQueue.take();
            }
            System.out.println("全部消费完毕");
        }
    }
}
