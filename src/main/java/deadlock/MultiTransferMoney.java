package deadlock;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.Collections;
import java.util.Random;

/**
 * @Description:
 * @Author: mtdp
 * @Date: 2020-07-26
 */

public class MultiTransferMoney {
    private static final int NUM_ACCOUNTS = 500;
    private static final int NUM_MONEY = 1000;
    private static final int NUM_ITERATION = 1000000;
    private static final int NUM_THREAD = 20;

    public static void main(String[] args) {
        Random random = new Random();
        TransferMoney.Account[] accounts = new TransferMoney.Account[NUM_ACCOUNTS];
        for (int i = 0; i < accounts.length; i++) {
            accounts[i] = new TransferMoney.Account(NUM_MONEY);
        }

        class MyThread extends Thread {
            @Override
            public void run() {
                for (int i = 0; i < NUM_ITERATION; i++) {
                    int fromAccount = random.nextInt(NUM_ACCOUNTS);
                    int toAccount = random.nextInt(NUM_ACCOUNTS);
                    int amount = random.nextInt(NUM_MONEY);
                    TransferMoney.transferMoney(accounts[fromAccount], accounts[toAccount], amount);
                }
            }
        }

        for (int i = 0; i < NUM_THREAD; i++) {
            new MyThread().start();
        }
    }
}
