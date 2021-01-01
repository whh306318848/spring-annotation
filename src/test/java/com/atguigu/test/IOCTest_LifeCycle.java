package com.atguigu.test;

import com.atguigu.config.MainConfigOfLifeCycle;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author: wuhaohua
 * @date: Created in 2020/12/29 15:51
 * @description: TODO
 */
public class IOCTest_LifeCycle {

    @Test
    public void test01() {
        // 1、创建IOC容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfLifeCycle.class);
        System.out.println("容器创建完成... ");

//        applicationContext.getBean("car");
        // 关闭容器
        applicationContext.close();
        System.out.println("容器创建完成... ");
    }
}
