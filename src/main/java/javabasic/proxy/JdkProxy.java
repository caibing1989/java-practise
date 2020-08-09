package javabasic.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Description: jdk自带的动态代理模式
 * @Author: mtdp
 * @Date: 2020-07-20
 */

public class JdkProxy {
    public static void main(String[] args) {
        IAct iStar = new Start();
        IAct iBroker = (IAct) Proxy.newProxyInstance(IAct.class.getClassLoader(), iStar.getClass().getInterfaces(), new Broker(iStar));

        System.out.println("A老板要求唱歌");
        iBroker.doSing();
        System.out.println("B老板要求跳舞");
        iBroker.doDance();
    }


    static class Broker implements InvocationHandler {
        private Object targetObject;

        Broker(Object targetObject) {
            this.targetObject = targetObject;
        }

        @Override
        public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
            System.out.println("1、检查节目是否与明星的风格相匹配");
            System.out.println("2、出场费是否满足");

            // 明星do
            Object object = method.invoke(targetObject, objects);

            System.out.println("3、出场费尾款结算");
            System.out.println("4、粉丝签名会");

            return object;
        }
    }

    interface IAct {
        void doSing();

        void doDance();
    }

    // 明星
    static class Start implements IAct {
        @Override
        public void doSing() {
            System.out.println("明星开始唱歌");
        }

        @Override
        public void doDance() {
            System.out.println("明星开始跳舞");
        }
    }
}
