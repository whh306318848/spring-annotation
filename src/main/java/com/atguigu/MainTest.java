package com.atguigu;

import com.atguigu.bean.Person;
import com.atguigu.config.MainConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author: wuhaohua
 * @date: Created in 2020/12/28 10:49
 * @description: TODO
 */
public class MainTest {

    public static void main(String[] args) {
        // 使用配置文件的方式构造一个Bean
//        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        // 用id获取
//        Person bean = (Person) applicationContext.getBean("person");
//        System.out.println(bean);

        // 使用配置类的方式构造一个Bean
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
        // 用类型获取
        Person bean = applicationContext.getBean(Person.class);
        System.out.println(bean);

        String[] namesForType = applicationContext.getBeanNamesForType(Person.class);
        for (String name: namesForType) {
            System.out.println(name);
        }
    }
}
