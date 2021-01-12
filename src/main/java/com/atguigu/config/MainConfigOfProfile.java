package com.atguigu.config;

import com.atguigu.bean.Yellow;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.StringValueResolver;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * @author: wuhaohua
 * @date: Created in 2021/1/12 16:35
 * @description: TODO
 * Profile:
 *      Spring为我们提供的可以根据当前环境（开发、测试、生产等），动态的激活和切换一系列组件（配置、数据源、中间件等）的功能
 *
 *
 * @Profile：用于指定当前组件在哪个环境的情况下才能被注册到容器中，如果当前组件没有指定，则在任何环境下都能注册到容器中
 *      1、加了环境标识的bean，只有这个环境被激活时才能注册到容器中，默认情况下是default环境
 *      2、当@Profile注解标注在配置类上时，只有是指定的环境时，整个配置类里面的所有配置才会生效
 *      3、没有标注环境标识的bean，在任何环境下都是会被加载的
 */
//@Profile("test")
@PropertySource("classpath:/dbconfig.properties")
@Configuration
public class MainConfigOfProfile implements EmbeddedValueResolverAware {

    @Value("${db.user}")
    private String user;

    private StringValueResolver valueResolver;

    private String diverClass;

//    @Profile("test")
    @Bean("yellow")
    public Yellow yellow() {
        return new Yellow();
    }

    @Profile("test")
    @Bean("testDataSource")
    public DataSource dataSourceTest(@Value("${db.password}") String pwd) throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser(user);
        dataSource.setPassword(pwd);
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/test");
        dataSource.setDriverClass(diverClass);
        return dataSource;
    }

    @Profile("dev")
    @Bean("devDataSource")
    public DataSource dataSourceDev(@Value("${db.password}") String pwd) throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser(user);
        dataSource.setPassword(pwd);
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/pc");
        dataSource.setDriverClass(diverClass);
        return dataSource;
    }

    @Profile("prod")
    @Bean("prodDataSource")
    public DataSource dataSourceProd(@Value("${db.password}") String pwd) throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser(user);
        dataSource.setPassword(pwd);
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/sw-zs");
        dataSource.setDriverClass(diverClass);
        return dataSource;
    }

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        this.valueResolver = resolver;
        this.diverClass = valueResolver.resolveStringValue("${db.driverClass}");
    }
}
