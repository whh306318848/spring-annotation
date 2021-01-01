package com.atguigu.config;

import com.atguigu.bean.Person;
import com.atguigu.service.BookService;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

/**
 * @author: wuhaohua
 * @date: Created in 2020/12/28 11:04
 * @description: 配置类就等于以前的配置文件
 */
@Configuration  // 告诉Spring这是一个配置类
// 配置多个包扫描规则
@ComponentScans(value = {
        @ComponentScan(value = "com.atguigu", includeFilters = {
                // 按照注解排除包扫描
//                @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Controller.class}),
//                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {BookService.class}),
                @ComponentScan.Filter(type = FilterType.CUSTOM, classes = {MyTypeFilter.class})
        }, useDefaultFilters = false)   // 配置包扫描的范围
})
/***
 * @ComponentScan value：指定要扫描的包
 * excludeFilters = Filter[] ：指定扫描的时候按照什么规则排除哪些组件
 * includeFilters = Filter[] ：指定扫描的时候只需要包含哪些组件
 * FilterType.ANNOTATION ：按照注解进行过滤
 * FilterType.ASSIGNABLE_TYPE ：按照给定的类型过滤，包含其子类、实现类等
 * FilterType.ASPECTJ ：按照ASPECTJ表达式过滤
 * FilterType.REGEX ：按照正则表达式过滤
 * FilterType.CUSTOM ：按照自定义规则规律，自定义规则需要实现TypeFilter接口
 **/
public class MainConfig {

    // 给容器中注册一个Bean，类型为返回值的类型，id默认是用方法名作为id
    @Bean(value = "person") // 通过value指定id
    public Person person01() {
        return new Person("lisi", 20);
    }
}
