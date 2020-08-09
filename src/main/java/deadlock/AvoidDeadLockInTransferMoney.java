package deadlock;


/**
 * @Description:
 * @Author: mtdp
 * @Date: 2020-07-26
 */

public class AvoidDeadLockInTransferMoney implements Runnable {
    private int flag = 1;
    private static TransferMoney.Account a = new TransferMoney.Account(500);
    private static TransferMoney.Account b = new TransferMoney.Account(500);
    private static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        AvoidDeadLockInTransferMoney transferMoney1 = new AvoidDeadLockInTransferMoney();
        AvoidDeadLockInTransferMoney transferMoney2 = new AvoidDeadLockInTransferMoney();
        Thread thread1 = new Thread(transferMoney1);
        Thread thread2 = new Thread(transferMoney2);
        transferMoney1.flag = 1;
        transferMoney2.flag = 0;
        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

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

    static void transferMoney(TransferMoney.Account from, TransferMoney.Account to, int money) {
        int fromHash = System.identityHashCode(from);
        int toHash = System.identityHashCode(to);

        if (fromHash > toHash) {
            synchronized (from) {
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (to) {
                    doTransferMoney(from, to, money);
                }
            }
        } else if (fromHash < toHash) {
            synchronized (to) {
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (from) {
                    doTransferMoney(from, to, money);
                }
            }
        } else {
            // 如果fromHash == toHash，打加时赛
            synchronized (lock) {
                synchronized (to) {
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (from) {
                        doTransferMoney(from, to, money);
                    }
                }
            }
        }
    }

    static void doTransferMoney(TransferMoney.Account from, TransferMoney.Account to, int money) {
        if (from.balance < money) {
            System.out.println("没有余额，转账失败");
            return;
        }
        from.balance -= money;
        to.balance += money;
        System.out.println("成功转账" + money + "元");
    }
}
