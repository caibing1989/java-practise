package jvm.classinit;

/**
 * @Description: 演示类的初始化顺序
 * @Author: mtdp
 * @Date: 2020-08-02
 */

public class InitOrder {
    // 1
    private static InitOrder initOrder = new InitOrder();

    // 3
    private static int a = 0;
    private static int b;

    // 2
    private InitOrder() {
        a++;
        b++;
        System.out.println("a++ = " + a + " b++ = " + b);
    }

    // 静态方法被调用时，类会被初始化
    static InitOrder getInstance() {
        System.out.println("getInstance");
        return initOrder;
    }

    public int getA() {
        return a;
    }

    int getB() {
        return b;
    }
}

class Test {
    public static void main(String[] args) {
        InitOrder initOrder = InitOrder.getInstance();

        System.out.println("a = " + initOrder.getA() + "; b = " + initOrder.getB());
    }
}
