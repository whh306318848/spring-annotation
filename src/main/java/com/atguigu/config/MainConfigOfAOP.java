package com.atguigu.config;

import com.atguigu.aop.LogAspects;
import com.atguigu.aop.MathCalculator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author: wuhaohua
 * @date: Created in 2021/1/13 15:43
 * @description: AOP配置类
 * AOP【动态代理】：指在程序运行期间，动态的将某段代码切入到指定方法指定位置进行运行的变成方式
 *
 * 使用AOP步骤：
 * 1、在pom文件中导入AOP模块，String AOP模块做了一些简化，只需要配置几个注解就可以运行起来
 * 2、定义业务逻辑类MathCalculator，在业务逻辑运行的时候将日志进行打印（方法运行之前、方法运行结束、方法出现异常等情况）
 * 3、定义一个日志切面类LogAspects，切面类里面的方法需要动态感知MathCalculator.div运行到哪里进行执行
 *      通知方法：
 *          前置通知（@Before）：相当于logStart，在目标方法运行之前运行
 *          后置通知（@After）：相当于logEnd，在目标方法运行结束之后运行（无论方法正常结束还是异常结束都调用）
 *          返回通知（@AfterReturning）：相当于logReturn，在目标方法正常返回之后运行
 *          异常通知（@AfterThrowing）：相当于logException，在目标方法运行出现异常以后运行
 *          环绕通知（@Around）：其是一个动态代理，可以手动推进目标方法运行（joinPoint.proceed()），是最底层的通知
 * 4、给切面类的目标方法标注何时何地运行（标志通知注解）
 * 5、将切面类和业务逻辑类（目标方法所在类）都加入到容器中
 * 6、必须告诉Sring哪个类是切面类，给切面类上加上@Aspect注解
 * 7、给配置类上加@EnableAspectJAutoProxy注解，开启基于注解的AOP模式
 *      在Spring中有很多的@EnableXXX注解，其作用都是开启XXX
 *
 * 使用Spring AOP最重要三步总结：
 *  1、将业务逻辑组件和切面类都加入到容器中，并告诉Spring哪个是切面类（@Aspect注解）
 *  2、在切面类上的每一个通知方法上标注通知注解，告诉Spring何时何地运行（切入点表达式）
 *  3、开启基于注解的AOP模式（@EnableAspectJAutoProxy注解）
 */
@EnableAspectJAutoProxy
@Configuration
public class MainConfigOfAOP {

    // 将业务逻辑类加入容器中
    @Bean
    public MathCalculator mathCalculator() {
        return new MathCalculator();
    }

    // 将切面类加入容器中
    @Bean
    public LogAspects logAspects() {
        return new LogAspects();
    }
}
