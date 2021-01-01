package com.atguigu.config;

import com.atguigu.bean.Car;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @author: wuhaohua
 * @date: Created in 2020/12/29 15:45
 * @description: Bean的生命周期：bean创建--初始化--销毁的过程
 * 容器管理bean的生命周期：
 * 我们可以自定义初始化和销毁方法：容器在bean进行当前生命周期的时候来调用我们自定义的初始化和销毁方法
 *
 * 构造（对象创建）
 *      单实例：在容器启动的时候创建对象
 *      多实例：在每次获取的时候创建对象
 * 初始化：
 *      对象创建完成，并赋值号，调用初始化方法
 * 销毁：
 *      单实例：在容器关闭的时候进行销毁
 *      多实例：容器不会管理这个bean，所以容器不会调用销毁方法
 *
 * 遍历得到所有的BeanPostProcess，挨个执行beforeInitialization，一旦方法返回null，调出for循环，不会执行后面的BeanPostProcess
 *
 * BeanPostProcess的原理
 * populateBean(beanName, mbd, instanceWrapper);给bean进行属性赋值
 * initializeBean {
 *     applyBeanPostProcessorsBeforeInitialization(wrappedBean, beanName);
 *     invokeInitMethods(beanName, wrappenBean, mbd);执行自定义初始化
 *     applyBeanPostProcessAfterInitialization(wrappedBean, beanName);
 * }
 *
 *
 * 1、指定初始化和销毁方法：
 *      a、在xml配置文件中指定init-method=""和destroy-method=""
 *      b、通过@Bean注解指定init-method=""和destroy-method=""
 * 2、通过让Bean实现InitializingBean接口定义初始化逻辑，实现DisposableBean接口定义销毁逻辑
 * 3、可以使用JSR250规范中定义的两个注解
 *      @PostConstruct：在Bean装配完成后，即对象创建，属性赋值完成后，执行初始化，是一个标注在方法上的注解
 *      @PreDestroy：在容器销毁bean之前，通知我们进行清理工作
 * 4、BeanPostProcessor接口：bean的后置处理器：在bean初始化前后进行一些处理工作
 *      postProcessBeforeInitialization：在初始化之前工作
 *      postProcessAfterInitialization：在初始化之后工作
 *
 * Spring底层对BeanPostProcessor的使用
 *      bean赋值，其他组件注入，@Autowired，生命周期注解功能，@Async等功能，都是用BeanPostProcessor实现的
 */
@ComponentScan("com.atguigu.bean")
@Configuration
public class MainConfigOfLifeCycle {

    //@Scope("prototype")
    @Bean(initMethod = "init", destroyMethod = "destory")
    public Car car() {
        return new Car();
    }
}
