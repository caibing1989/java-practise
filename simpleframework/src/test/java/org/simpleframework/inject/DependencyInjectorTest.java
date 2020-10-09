package org.simpleframework.inject;

import com.cb.controller.frontend.MainPageController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.simpleframework.core.BeanContainer;

/**
 * @Description:
 * @Author: mtdp
 * @Date: 2020-10-05
 */

public class DependencyInjectorTest {
    private static BeanContainer beanContainer;

    @BeforeAll
    static void init() {
        beanContainer = BeanContainer.getInstance();
    }

    @DisplayName("测试依赖注入: doIocTest")
    @Test
    public void doIocTest() {
        beanContainer.loadBeans("com.cb");
        Assertions.assertTrue(beanContainer.isLoaded());
        MainPageController mainPageController = (MainPageController) beanContainer.getBean(MainPageController.class);
        Assertions.assertNotNull(mainPageController);
        Assertions.assertNull(mainPageController.getHeadLineShopCategoryCombineService());
        new DependencyInjector().doIoc();
        Assertions.assertNotNull(mainPageController.getHeadLineShopCategoryCombineService());
    }
}
