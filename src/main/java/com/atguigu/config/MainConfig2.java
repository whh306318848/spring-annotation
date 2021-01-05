package com.atguigu.config;

import com.atguigu.bean.Color;
import com.atguigu.bean.ColorFactoryBean;
import com.atguigu.bean.Person;
import com.atguigu.bean.Red;
import com.atguigu.condition.LinuxCondition;
import com.atguigu.condition.MyImportBeanDefinitionRegistrar;
import com.atguigu.condition.MyImportSelector;
import com.atguigu.condition.WindowsCondition;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.*;

/**
 * @author: wuhaohua
 * @date: Created in 2020/12/28 16:34
 * @description: 组件管理
 */
// 类中组件统一设置，满足当前条件，这个类中配置的所有bean注册才会生效
@Conditional({WindowsCondition.class})
@Configuration
// 导入组件，id默认是组件的全类名（含包名）
@Import({Color.class, Red.class, MyImportSelector.class, MyImportBeanDefinitionRegistrar.class})
public class MainConfig2 {

    // 默认是单实例的
    /***
     * ConfigurableBeanFactory.SCOPE_PROTOTYPE  prototype：多实例，IOC容器启动时并不会去调用方法创建对象放在容器中，而是每次获取的时候都会调用方法创建对象
     * ConfigurableBeanFactory.SCOPE_SINGLETON  singleton：单实例（默认值），IOC容器启动会调用方法创建对象到IOC容器中，以后每次获取就是直接从容器（map.get()）中拿
     * org.springframework.web.context.WebApplicationContext.SCOPE_REQUEST  request：同一次请求创建一个实例
     * org.springframework.web.context.WebApplicationContext.SCOPE_SESSION  session：同一个session创建一个实例
     *
     * 使用@Scope注解调整作用域
     *
     * @Lazy懒加载：
     *      单实例bean：默认在容器启动的时候创建对象
     *      懒加载：容器启动时不创建对象，而是在第一次使用（获取）Bean时创建对象，并初始化
     **/
//    @Scope("prototype")
    @Lazy
    @Bean("person")
    public Person person() {
        System.out.println("给容器中添加Person...");
        return new Person("张三", 25);
    }

    /***
     * @Conditional({Condition})：按照一定的条件进行判断，满足条件给容器中注册bean
     * 如果系统是Windows，就给容器中注册("bill")
     * 如果系统是Linux，就给容器中注册("linus")
     **/
    @Conditional({WindowsCondition.class})
    @Bean("bill")
    public Person person01() {
        return new Person("Bill Gates", 62);
    }

    @Conditional({LinuxCondition.class})
    @Bean("linus")
    public Person person02() {
        return new Person("Linus", 48);
    }

    /***
     * 给容器中注册组件
     * 1、包扫描+组件标注注解（@Controller/@Service/@Repository/@Component）[自己写的类]
     * 2、@Bean[导入的第三方包里面的组件]
     * 3、@Import[快速给容器中导入一个组件]
     *      a、@Import(要导入到容器中的组件)，容器中就会自动注册这个组件，id默认是全类名
     *      b、ImportSelector：返回需要导入的组件的全类名数组，在SpringBoot源码中用的比较多
     *      c、ImportBeanDefinitionRegistrar：自定义给容器中注册bean（手动注册bean到容器中），在SpringBoot源码中用的比较多
     * 4、使用Sprig提供的FactoryBean（工厂Bean）
     *      a、默认获取到的是工厂Bean调用getObject创建的对象
     *      b、要获取工厂Bean本身，我们要给id前面加一个&
     **/

    @Bean
    public ColorFactoryBean colorFactoryBean() {
        return new ColorFactoryBean();
    }
}
