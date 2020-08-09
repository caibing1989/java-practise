package jvm.classinit;

/**
 * @Description: 演示调用类的静态变量会初始化这个类
 * @Author: mtdp
 * @Date: 2020-08-02
 */

public class CallStaticVariableOfClass {
    public static void main(String[] args) {
        System.out.println("MyChild.a :" + MyChild.a);
    }
}
