package demo.pattern.factory.entity;

/**
 * @Description:
 * @Author: mtdp
 * @Date: 2020-09-06
 */

public class DellKeyboard implements Keyboard {
    @Override
    public void sayHello() {
        System.out.println("我是Dell键盘");
    }
}
