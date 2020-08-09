package jvm.classinit;

/**
 * @Description: 演示jvm启动的时候会主动初始化main所在的类
 * @Author: mtdp
 * @Date: 2020-08-02
 */

public class RunMainClassInit {
    static {
        System.out.println("主动初始化该类");
    }

    public static void main(String[] args) {

    }
}
