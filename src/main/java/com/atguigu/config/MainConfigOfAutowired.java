package com.atguigu.config;

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
 */
@Configuration
@ComponentScan({"com.atguigu.service", "com.atguigu.dao", "com.atguigu.controller"})
public class MainConfigOfAutowired {

    @Primary
    @Bean("bookDao2")
    public BookDao bookDao() {
        BookDao bookDao = new BookDao();
        bookDao.setLable("2");
        return bookDao;
    }
}
