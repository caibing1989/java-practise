package demo.pattern.factory.abstractf;

/**
 * @Description:
 * @Author: mtdp
 * @Date: 2020-09-06
 */

public class AbstractFactoryDemo {
    public static void main(String[] args) {
        ComputerFactory computerFactory = new HPComputerFactory();
        computerFactory.createMouse().sayHi();
        computerFactory.createKeyboard().sayHello();
    }
}
