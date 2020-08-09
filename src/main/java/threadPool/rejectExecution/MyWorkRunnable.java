package threadPool.rejectExecution;

import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;

/**
 * @Description:
 * @Author: mtdp
 * @Date: 2020-07-07
 */

public class MyWorkRunnable implements Runnable {
    int messageNumber;

    public MyWorkRunnable(int messageNumber) {
        this.messageNumber = messageNumber;
    }

    @Override
    public void run() {
        try {
            sleep(2000);
            System.out.println("MyWorkRunnable " + currentThread().getName() + " 任务" + messageNumber + " start");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("MyWorkRunnable " + currentThread().getName() + " 任务" + messageNumber + " complete");
    }
}
