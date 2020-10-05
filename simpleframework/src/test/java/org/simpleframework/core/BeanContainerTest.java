package org.simpleframework.core;

import com.cb.controller.frontend.MainPageController;
import org.junit.jupiter.api.*;
import org.simpleframework.core.annotation.Controller;

import java.util.Set;

/**
 * @Description:
 * @Author: mtdp
 * @Date: 2020-10-04
 */

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BeanContainerTest {
    private static BeanContainer beanContainer;

    @BeforeAll
    static void init() {
        beanContainer = BeanContainer.getInstance();
    }

    @DisplayName("加载目标类及其实例到BeanContainer: loadBeansTest")
    @Test
    @Order(1)
    public void loadBeansTest() {
        Assertions.assertFalse(beanContainer.isLoaded());
        beanContainer.loadBeans("com.cb");
        Assertions.assertEquals(6, beanContainer.size());
        Assertions.assertTrue(beanContainer.isLoaded());
    }

    @DisplayName("根据类获取实例: getBeanTest")
    @Test
    @Order(2)
    public void getBeanTest() {
        MainPageController mainPageController = (MainPageController) beanContainer.getBean(MainPageController.class);
        Assertions.assertNotNull(mainPageController);
    }

    @DisplayName("根据注解获取对应的实例: getClassesByAnnotationTest")
    @Test
    @Order(3)
    public void getClassesByAnnotationTest() {
        Set<Class<?>> set = beanContainer.getClassesByAnnotation(Controller.class);
        Assertions.assertEquals(3, set.size());
    }
}
