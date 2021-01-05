package com.atguigu.config;

import com.atguigu.bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author: wuhaohua
 * @date: Created in 2021/1/5 14:28
 * @description: 属性赋值配置类
 */
// 使用@PropertySource读取外部配置文件中的key/value保存到运行的环境变量中，加载完外部的配置文件以后，使用${}去除配置文件的值
@PropertySource(value = {"classpath:/person.properties"})
@Configuration
public class MainConfigOfPropertyValues {

    @Bean
    public Person person() {
        return new Person();
    }
}
