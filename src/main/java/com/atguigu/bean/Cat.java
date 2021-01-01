package com.atguigu.bean;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @author: wuhaohua
 * @date: Created in 2020/12/29 16:48
 * @description: TODO
 */
@Component
public class Cat implements InitializingBean, DisposableBean {

    public Cat() {
        System.out.println("cat constructor...");
    }

    /***
     * @Author: wuhaohua
     * @Date: 2020/12/29
     * @Description: 在Bean销毁之前被调用
     **/
    @Override
    public void destroy() throws Exception {
        System.out.println("cat destroy...");

    }

    /***
     * @Author: wuhaohua
     * @Date: 2020/12/29
     * @Description: 在Bean构建之后，初始化之前被调用
     **/
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("cat afterPropertiesSet...");
    }
}
