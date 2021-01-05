package com.atguigu.test;

import com.atguigu.bean.Person;
import com.atguigu.config.MainConfigOfLifeCycle;
import com.atguigu.config.MainConfigOfPropertyValues;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @author: wuhaohua
 * @date: Created in 2020/12/29 15:51
 * @description: 属性赋值测试类
 */
public class IOCTest_PropertyValue {
    // 1、创建IOC容器
    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfPropertyValues.class);

    @Test
    public void test01() {
        printBeans(this.applicationContext);
        System.out.println("===================");

        Person person = (Person) applicationContext.getBean("person");
        System.out.println(person);

        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        String property = environment.getProperty("person.nickName");
        System.out.println(property);

        // 关闭容器
        applicationContext.close();
        System.out.println("容器创建完成... ");
    }

    private void printBeans(AnnotationConfigApplicationContext applicationContext) {
        String[] definitionNames = applicationContext.getBeanDefinitionNames();
        for (String name : definitionNames) {
            System.out.println(name);
        }
    }
}
