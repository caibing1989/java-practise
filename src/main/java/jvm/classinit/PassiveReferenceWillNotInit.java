package jvm.classinit;

/**
 * @Description: 演示被动引用不是初始化的情况，例如子类引用父类的静态字段，不会初始化子类
 * @Author: mtdp
 * @Date: 2020-08-02
 */

public class PassiveReferenceWillNotInit {
    public static void main(String[] args) {
        System.out.println("My child reference home: " + MyChild.home);

        // 通过数组定义来引用类，也不会触发这个类的初始化
        MyChild[] myChildren = new MyChild[2];

        // 访问该类的常量时，不会触发这个类的初始化
        System.out.println("My child b: " + MyChild.b);
    }
}
