package com.atguigu.config;

import com.atguigu.bean.Car;
import com.atguigu.bean.Color;
import com.atguigu.dao.BookDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @author: wuhaohua
 * @date: Created in 2021/1/5 17:22
 * @description: 自动装配配置类
 * 自动装配：
 * Spring利用依赖注入（DI），完成对IOC容器中各个组件的依赖关系赋值；
 * 1)、使用@Autowired注解自动注入，其原理是：
 *      a.默认优先按照类型去容器中找对应的组件，使用applicationContext.getBean(BookDao.class);
 *      b.如果找到相同类型的组件，再将属性的名称作为组件的id去容器中查找applicationContext.getBean("bookDao")
 *      c.在被装配的变量上（即使用@Autowired注解的地方），使用@Qualifier注解指定要装配的组件的id，而不是使用属性名
 *      d.默认情况下，一定要将属性赋值好，如果没有找到就会报错，但也可以使用@Autowired(required = false)设置自动装配是非必须的，能装配则装配，如果找不到就不装配
 *      e.在组件上（即组件注册的地方，如@Bean、@Service等注解上），使用@Primary注解，让Spring进行自动装配的时候，默认使用首选的Bean，也可以继续使用@Qualifier注解指定需要装配的bean的名字
 * 2)、Spring还支持使用@Resource(JSR250)和@Inject(JSR330)，这两个注解是Java规范的注解，而@Autowired是Spring的注解
 *      a.@Resource可以和@Autowired一样实现自动装配功能，但其默认是按照属性对应的组件名称进行装配的
 *      b.@Resource若需要装配指定的Bean，可以使用其name属性进行设置
 *      c.@Resource不支持@Primary功能，且不支持@Autowired(required = false)功能
 *      d.如果要使用@Inject注解，需要先导入Javax Inject依赖
 *      e.@Inject支持@Primary功能，但不支持@Autowired(required = false)功能
 * 3)、AutowiredAnnotationBeanPostProcessor：负责解析完成自动装配，其是一个后置处理器，打开其源码可以看到其除了支持Autowired注解外，还支持Value、Inject、Lookup等注解
 * 4)、@Autowired可以标注在构造器、参数、方法、属性等位置实现自动注解
 *      a.标注在方法上
 *      b.标注在构造器上，如果组件只有一个有参构造器，这个有参构造器的@Autowired可以省略，参数位置的组件还是可以从容器中获取
 *      c.标注在参数位置上
 *      d.标注在@Bean标注的方法上：创建对象的时候，方法参数的值是从容器中获取的，默认不写@Autowired注解，效果是一样的，都能自动装配
 * 5)、自定义组件想要使用Spring容器底层的一些组件（如ApplicationContext,BeanFactory等）
 *      a.自定义组件需要实现xxxAware接口，在创建对象的时候，Spring会调用接口规定的方法注入相关组件
 *      b.把Spring底层一些组件注入到自定义的Bean中：xxxAware，功能使用xxxProcessor后置处理器实现，如ApplicationContextAware其是使用ApplicationContextAwareProcessor实现注入的
 */
@Configuration
@ComponentScan({"com.atguigu.service", "com.atguigu.dao", "com.atguigu.controller", "com.atguigu.bean"})
public class MainConfigOfAutowired {

    @Primary
    @Bean("bookDao2")
    public BookDao bookDao() {
        BookDao bookDao = new BookDao();
        bookDao.setLable("2");
        return bookDao;
    }

    /***
     * @Bean标的方法，创建对象的时候，方法参数的值是从容器中获取的
     **/
    @Bean
    public Color color(Car car) {
        Color color = new Color();
        color.setCar(car);
        return color;
    }
}
