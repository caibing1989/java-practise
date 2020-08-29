package spring.jdkandcglib.cglibstyle;

/**
 * @Description: 创建一个没有实现任何接口的类,因为没有实现接口该类无法使用JDK代理
 * @Author: mtdp
 * @Date: 2020-08-09
 */

public class HelloConcrete {
    public String sayHello(String str) {
        return "HelloConcrete: " + str;
    }
}
