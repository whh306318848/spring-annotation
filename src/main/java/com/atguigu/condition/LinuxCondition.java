package com.atguigu.condition;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author: wuhaohua
 * @date: Created in 2020/12/28 17:53
 * @description: 判断是否Linux操作系统
 */
public class LinuxCondition implements Condition {
    /***
     * @param conditionContext 判断能使用的上下文（环境）
     * @param annotatedTypeMetadata 当前标注了Condition注解的注释信息
     * @throws
     * @Author: wuhaohua
     * @Date: 2020/12/28
     * @Description:
     **/
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        // 1、能获取到ioc使用的beanfactory
        ConfigurableListableBeanFactory beanFactory = conditionContext.getBeanFactory();
        // 2、获取类加载器
        ClassLoader classLoader = conditionContext.getClassLoader();
        // 3、获取到环境信息
        Environment environment = conditionContext.getEnvironment();
        // 4、获取到bean定义的注册类
        BeanDefinitionRegistry registry = conditionContext.getRegistry();

        String property = environment.getProperty("os.name");
        if (property.contains("Linux")) {
            return true;
        }
        return false;
    }
}
