package demo.pattern.factory.entity;

/**
 * @Description:
 * @Author: mtdp
 * @Date: 2020-09-06
 */

public class IBMMouse implements Mouse {
    @Override
    public void sayHi() {
        System.out.println("我是HP旗下的IBM鼠标");
    }
}
