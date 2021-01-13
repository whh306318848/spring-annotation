package com.atguigu.test;

import com.atguigu.aop.MathCalculator;
import com.atguigu.bean.Boss;
import com.atguigu.bean.Car;
import com.atguigu.bean.Color;
import com.atguigu.config.MainConfigOfAOP;
import com.atguigu.config.MainConfigOfAutowired;
import com.atguigu.service.BookService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author: wuhaohua
 * @date: Created in 2020/12/29 15:51
 * @description: Bean生命周期测试类
 */
public class IOCTest_AOP {

    @Test
    public void test01() {
        // 1、创建IOC容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfAOP.class);
        System.out.println("容器创建完成... ");

        // 不要自己创建对象，需要使用Spring容器中的组件
//        MathCalculator calculator = new MathCalculator();
//        calculator.div(1, 1);
        MathCalculator calculator = applicationContext.getBean(MathCalculator.class);
        calculator.div(6, 0);

        // 关闭容器
        applicationContext.close();
        System.out.println("容器创建完成... ");
    }
}
