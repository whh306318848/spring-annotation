package com.atguigu.test;

import com.atguigu.bean.Boss;
import com.atguigu.bean.Car;
import com.atguigu.bean.Color;
import com.atguigu.bean.Yellow;
import com.atguigu.config.MainConfigOfAutowired;
import com.atguigu.config.MainConfigOfProfile;
import com.atguigu.service.BookService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;

/**
 * @author: wuhaohua
 * @date: Created in 2020/12/29 15:51
 * @description: Profile注解测试类
 * 切换环境的方式：
 * 1、使用命令行参数，在JVM参数位置加载-Dspring.profiles.active=test
 * 2、使用代码的方式激活某种环境
 */
public class IOCTest_Profile {

    @Test
    public void test01() {
        // 1、创建IOC容器
//        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfProfile.class);

        // 1、创建一个applicationContext
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 2、设置需要激活的环境
        applicationContext.getEnvironment().setActiveProfiles("dev");
        // 3、注册主配置类
        applicationContext.register(MainConfigOfProfile.class);
        // 4、启动刷新容器
        applicationContext.refresh();

        System.out.println("容器创建完成... ");

        String[] namesForType = applicationContext.getBeanNamesForType(DataSource.class);
        for (String name: namesForType) {
            System.out.println(name);
        }

        Yellow bean = applicationContext.getBean(Yellow.class);
        System.out.println(bean);

        // 关闭容器
        applicationContext.close();
        System.out.println("容器创建完成... ");
    }
}
