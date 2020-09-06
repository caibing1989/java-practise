package demo.pattern.factory.entity;

/**
 * @Description:
 * @Author: mtdp
 * @Date: 2020-09-06
 */

public class DellMouse implements Mouse {
    @Override
    public void sayHi() {
        System.out.println("我是Dell鼠标");
    }
}
