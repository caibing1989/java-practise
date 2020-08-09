package javabasic.proxy;

/**
 * @Description:  静态代理
 * @Author: mtdp
 * @Date: 2020-07-17
 */

public class MySimpleProxy {

    public static void main(String[] args) {
        Broker broker = new Broker();

        System.out.println("A老板要求唱歌");
        broker.doSing();
        System.out.println("B老板要求跳舞");
        broker.doDance();
    }

    // 经纪人
    static class Broker {
        void doSing() {
            System.out.println("1、检查节目是否与明星的风格相匹配");
            System.out.println("2、出场费是否满足");
            new Start().doSing();
            System.out.println("3、出场费尾款结算");
            System.out.println("4、粉丝签名会");
        }

        void doDance() {
            System.out.println("1、检查节目是否与明星的风格相匹配");
            System.out.println("2、出场费是否满足");
            new Start().doDance();
            System.out.println("3、出场费尾款结算");
            System.out.println("4、粉丝签名会");
        }
    }

    // 明星
    static class Start {
        void doSing() {
            System.out.println("明星开始唱歌");
        }

        void doDance() {
            System.out.println("明星开始跳舞");
        }
    }

}
