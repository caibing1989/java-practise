package spring.jdkandcglib.jdkstyle;

/**
 * @Description: 被代理类
 * @Author: mtdp
 * @Date: 2020-08-09
 */

public class RealSubject implements Subject {
    @Override
    public void request() {
        System.out.println("from real subject");
    }
}
