package com.atguigu.condition;


import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author: wuhaohua
 * @date: Created in 2020/12/28 17:54
 * @description: 判断是否Windows操作系统
 */
public class WindowsCondition implements Condition {
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

        // 可以判断容器中bean注册情况，也可以给容器中注册bean
        boolean definition = registry.containsBeanDefinition("person");

        if (property.contains("Windows")) {
            return true;
        }
        return false;
    }
}
