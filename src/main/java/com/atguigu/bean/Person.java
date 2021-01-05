package com.atguigu.bean;

import org.springframework.beans.factory.annotation.Value;

/**
 * @author: wuhaohua
 * @date: Created in 2020/12/28 10:45
 * @description: TODO
 */
public class Person {
    // 使用@Value赋值：
    // 1、基本数值
    // 2、可以写Spring表达式SpEL：#{}
    // 3、可以使用${}取配置文件【.properties】中的值（在运行环境变量中的值）
    @Value("张三")
    private String name;
    @Value("#{20-2}")
    private Integer age;
    @Value("${person.nickName}")
    private String nickName;

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Person() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
