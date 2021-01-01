package com.atguigu.bean;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author: wuhaohua
 * @date: Created in 2020/12/29 17:16
 * @description: TODO
 */
@Component
public class Dog implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    public Dog() {
        System.out.println("dog constructor...");
    }

    /***
     * @Author: wuhaohua
     * @Date: 2020/12/29
     * @Description: 对象创建并赋值之后调用
     **/
    @PostConstruct
    public void init() {
        System.out.println("dog PostConstruct...");
    }

    /***
     * @Author: wuhaohua
     * @Date: 2020/12/29
     * @Description: 容器移除对象之前调用
     **/
    @PreDestroy
    public void destory() {
        System.out.println("dog PreDestroy...");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
