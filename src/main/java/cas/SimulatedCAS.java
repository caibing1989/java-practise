package cas;

/**
 * @Description: cas等价代码
 * @Author: mtdp
 * @Date: 2020-08-01
 */

public class SimulatedCAS {

    private volatile int value;

    public synchronized int compareAndSwap(int expectValue, int newValue) {
        int oldValue = value;
        if (oldValue == expectValue) {
            oldValue = newValue;
        }
        return oldValue;
    }
}
