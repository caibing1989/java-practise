package demo.pattern.factory.method;

/**
 * @Description:
 * @Author: mtdp
 * @Date: 2020-09-06
 */

public class FactoryMethodDemo {
    public static void main(String[] args) {
        MouseFactory mouseFactory = new HPMouseFactory();
        mouseFactory.createMouse().sayHi();
    }
}
