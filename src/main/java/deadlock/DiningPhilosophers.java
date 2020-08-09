package deadlock;


import java.util.Arrays;
import java.util.Random;

/**
 * @Description: 演示哲学家就餐问题导致的死锁问题
 * @Author: mtdp
 * @Date: 2020-07-26
 */

public class DiningPhilosophers {
    public static void main(String[] args) {
        Philosopher[] philosophers = new Philosopher[5];
        Object[] chopsticks = new Object[philosophers.length];

        for (int i = 0; i < chopsticks.length; i++) {
            chopsticks[i] =  new Object();
        }
        for (int i = 0; i < philosophers.length; i++) {
            Object leftChopstick = chopsticks[i];
            Object rightChopstick = chopsticks[(i + 1) % chopsticks.length];
            philosophers[i] = new Philosopher(leftChopstick, rightChopstick);
            new Thread(philosophers[i], "哲学家" + i + "号").start();
        }
    }

    static class Philosopher implements Runnable {
        private Object leftChopstick;
        private Object rightChopstick;

        Philosopher(Object leftChopstick, Object rightChopstick) {
            this.leftChopstick = leftChopstick;
            this.rightChopstick = rightChopstick;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    doAction("thinking");
                    synchronized (leftChopstick) {
                        doAction("pick up left chopstick");
                        synchronized (rightChopstick) {
                            doAction("pick up right chopstick to eat");

                            doAction("put down right chopstick");
                        }
                        doAction("put down left chopstick");
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        private void doAction(String action) throws InterruptedException {
            System.out.println(Thread.currentThread().getName() + " " + action);
            Thread.sleep(new Random().nextInt(200));
        }
    }
}
