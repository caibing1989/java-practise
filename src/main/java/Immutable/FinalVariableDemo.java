package Immutable;

/**
 * @Description: 演示final变量
 * @Author: mtdp
 * @Date: 2020-08-01
 */

public class FinalVariableDemo {
    private final int a = 9;

    private final int b;

    private final int c;

    private static final int d = 99;

    private static final int e;

    public FinalVariableDemo(int b) {
        this.b = b;
    }

    {
        c = 9;
    }

    static {
        e = 89;
    }

    void testFinal() {
        // 不规定赋值时机，但是使用前必须赋值
        final int j;
    }
}
