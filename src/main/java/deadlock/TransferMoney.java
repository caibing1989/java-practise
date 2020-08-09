package deadlock;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * @Description: 转账时遇到死锁
 * @Author: mtdp
 * @Date: 2020-07-26
 */

public class TransferMoney implements Runnable {
    private int flag = 1;
    private static Account a = new Account(500);
    private static Account b = new Account(500);

    public static void main(String[] args) throws InterruptedException {
        TransferMoney transferMoney1 = new TransferMoney();
        TransferMoney transferMoney2 = new TransferMoney();
        transferMoney1.flag = 1;
        transferMoney2.flag = 0;
        Thread thread1 = new Thread(transferMoney1);
        Thread thread2 = new Thread(transferMoney2);
        thread1.start();
        thread2.start();

//        thread1.join();
//        thread2.join();

        Thread.sleep(1000);
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        long[] deadLock = threadMXBean.findDeadlockedThreads();
        if (deadLock != null && deadLock.length != 0) {
            for (long ele : deadLock) {
                ThreadInfo threadInfo = threadMXBean.getThreadInfo(ele);

                System.out.println("发现死锁" + threadInfo.getThreadName());
            }
        }

        System.out.println("Account a 余额" + a.balance);
        System.out.println("Account b 余额" + b.balance);
    }

    @Override
    public void run() {
        if (flag == 1) {
            transferMoney(a, b, 200);
        }

        if (flag == 0) {
            transferMoney(b, a, 200);
        }
    }

    static void transferMoney(Account from, Account to, int money) {
        synchronized (from) {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (to) {
                if (from.balance < money) {
                    System.out.println("没有余额，转账失败");
                    return;
                }
                from.balance -= money;
                to.balance += money;
                System.out.println("成功转账" + money + "元");
            }
        }
    }

    static class Account {
        int balance;

        Account(int money) {
            this.balance = money;
        }
    }
}
