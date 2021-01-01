package com.atguigu.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author: wuhaohua
 * @date: Created in 2020/12/29 18:02
 * @description: 后置处理器：初始化前后进行处理工作
 */
@Component
public class MyBeanPostProcessor implements BeanPostProcessor {

    /***
     * @param bean 刚创建的实例
     * @param beanName 这个实例在容器中的名字
     * @return 将要使用的bean实例
     * @Author: wuhaohua
     * @Date: 2020/12/29 
     * @Description:
     **/
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcessBeforeInitialization..." + beanName + "=>" + bean);
        return bean;
    }

    /***
     * @param bean 刚创建的实例
     * @param beanName 这个实例在容器中的名字
     * @return 将要使用的bean实例
     * @Author: wuhaohua
     * @Date: 2020/12/29
     * @Description:
     **/
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcessAfterInitialization..." + beanName + "=>" + bean);
        return bean;
    }
}
