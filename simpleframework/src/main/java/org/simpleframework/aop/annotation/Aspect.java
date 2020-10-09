package org.simpleframework.aop.annotation;

import org.simpleframework.core.annotation.Controller;

import java.lang.annotation.*;

/**
 * @Description:
 * @Author: mtdp
 * @Date: 2020-10-06
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Aspect {
    /**
     * 需要被织入横切逻辑的注解标签
     * 该属性已过期，不建议新的外部调用
     */
    @Deprecated
    Class<? extends Annotation> value() default Controller.class;

    String pointcut();
}
