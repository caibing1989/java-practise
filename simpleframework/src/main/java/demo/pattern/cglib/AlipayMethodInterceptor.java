package demo.pattern.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Description:
 * @Author: mtdp
 * @Date: 2020-10-07
 */

public class AlipayMethodInterceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        beforePay();
        Object result = methodProxy.invokeSuper(o, objects);
        afterPay();
        return result;
    }

    private void beforePay() {
        System.out.println("从招行取款");
    }

    private void afterPay() {
        System.out.println("支付出去");
    }
}
