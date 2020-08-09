package atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @Description: 演示AtomicIntegerFieldUpdater的用法
 * @Author: mtdp
 * @Date: 2020-08-01
 */

public class AtomicIntegerFieldUpdateDemo implements Runnable {
    private static Candidate tom = new Candidate();
    private static Candidate peter = new Candidate();

    private static AtomicIntegerFieldUpdater<Candidate> atomicIntegerFieldUpdater =
            AtomicIntegerFieldUpdater.newUpdater(Candidate.class, "score");

    static class Candidate {
        volatile int score;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            peter.score++;
            atomicIntegerFieldUpdater.getAndIncrement(tom);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        AtomicIntegerFieldUpdateDemo atomicIntegerFieldUpdateDemo = new AtomicIntegerFieldUpdateDemo();
        Thread thread1 = new Thread(atomicIntegerFieldUpdateDemo);
        Thread thread2 = new Thread(atomicIntegerFieldUpdateDemo);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        System.out.println("peter score: " + AtomicIntegerFieldUpdateDemo.peter.score);
        System.out.println("tom score: " + AtomicIntegerFieldUpdateDemo.tom.score);
    }
}
