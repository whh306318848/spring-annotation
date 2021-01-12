package com.atguigu.test;

import com.atguigu.bean.Boss;
import com.atguigu.bean.Car;
import com.atguigu.bean.Color;
import com.atguigu.config.MainConfigOfAutowired;
import com.atguigu.config.MainConfigOfLifeCycle;
import com.atguigu.dao.BookDao;
import com.atguigu.service.BookService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author: wuhaohua
 * @date: Created in 2020/12/29 15:51
 * @description: Bean生命周期测试类
 */
public class IOCTest_Autowired {

    @Test
    public void test01() {
        // 1、创建IOC容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfAutowired.class);
        System.out.println("容器创建完成... ");

        BookService bookService = applicationContext.getBean(BookService.class);
        System.out.println(bookService);

//        BookDao bookDao = applicationContext.getBean(BookDao.class);
//        System.out.println(bookDao);

        Boss boss = applicationContext.getBean(Boss.class);
        System.out.println(boss);
        Car car = applicationContext.getBean(Car.class);
        System.out.println(car);
        Color color = applicationContext.getBean(Color.class);
        System.out.println(color);

        System.out.println(applicationContext);

        // 关闭容器
        applicationContext.close();
        System.out.println("容器创建完成... ");
    }
}
