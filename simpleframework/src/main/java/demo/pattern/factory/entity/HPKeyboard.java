package demo.pattern.factory.entity;

/**
 * @Description:
 * @Author: mtdp
 * @Date: 2020-09-06
 */

public class HPKeyboard implements Keyboard {
    @Override
    public void sayHello() {
        System.out.println("我是HP键盘");
    }
}
