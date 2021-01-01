package com.atguigu.condition;

import com.atguigu.bean.RainBow;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author: wuhaohua
 * @date: Created in 2020/12/29 10:11
 * @description: TODO
 */
public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    /***
     * @param importingClassMetadata 当前类的所有注解及其他信息
     * @param registry bean定义的注册类，所有bean的定义都在这注册，可以通过这个变量给容器注册一个bean
     *                 把所有需要添加到容器中的bean：调用BeanDefinitionRegistry.registerBeanDefinition手工注册进来
     * @Author: wuhaohua
     * @Date: 2020/12/29 
     * @Description: 
     **/
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        boolean red = registry.containsBeanDefinition("com.atguigu.bean.Red");
        boolean blue = registry.containsBeanDefinition("com.atguigu.bean.Blue");
        if (red && blue) {
            // 指定Bean的定义信息，即bean的类型、bean的scope等等
            RootBeanDefinition beanDefinition = new RootBeanDefinition(RainBow.class);
            // 指定bean名
            registry.registerBeanDefinition("rainBow", beanDefinition);
        }
    }
}
