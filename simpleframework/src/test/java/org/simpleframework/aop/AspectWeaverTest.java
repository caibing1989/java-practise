package org.simpleframework.aop;

import com.cb.controller.superadmin.HeadLineOperationController;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.simpleframework.core.BeanContainer;
import org.simpleframework.inject.DependencyInjector;

/**
 * @Description:
 * @Author: mtdp
 * @Date: 2020-10-06
 */

public class AspectWeaverTest {
    private static BeanContainer beanContainer;

    @BeforeAll
    static void init() {
        beanContainer = BeanContainer.getInstance();
    }

    @DisplayName("织入通用逻辑测试: doAopTest")
    @Test
    public void doAopTest() {
        beanContainer.loadBeans("com.cb");
        new AspectWeaver().doAop();
        new DependencyInjector().doIoc();
        HeadLineOperationController headLineOperationController =
                (HeadLineOperationController) beanContainer.getBean(HeadLineOperationController.class);
        headLineOperationController.addHeadLine(null, null);
    }
}
