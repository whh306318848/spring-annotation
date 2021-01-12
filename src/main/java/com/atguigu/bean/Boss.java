package com.atguigu.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: wuhaohua
 * @date: Created in 2021/1/11 16:54
 * @description: TODO
 * 默认加在IOC容器中的组件，容器启动时会自动调用无参构造器创建对象，然后再进行初始化赋值等操作
 */
@Component
public class Boss {

    private Car car;

    // 构造器要用的组件，都是从容器中获取的
    @Autowired
    public Boss(Car car) {
        this.car = car;
        System.out.println("Boss...有参构造器...");
    }

    public Car getCar() {
        return car;
    }


    // 将@Autowired注解标注在方法上，Spring容器在创建当前对象时，就会调用该方法完成赋值
    // 方法使用的参数，自定义类型的值从IOC容器中获取
//    @Autowired
    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "Boss{" +
                "car=" + car +
                '}';
    }
}
