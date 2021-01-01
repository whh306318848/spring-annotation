package com.atguigu.bean;


import org.springframework.beans.factory.FactoryBean;

/**
 * @author: wuhaohua
 * @date: Created in 2020/12/29 15:31
 * @description: 创建一个Spring定义的FactoryBean
 */
public class ColorFactoryBean implements FactoryBean<Color> {
    /***
     * @return 需要添加到容器中的Color对象
     * @Author: wuhaohua
     * @Date: 2020/12/29 
     * @Description: 返回一个Color对应，这个对象会被添加到容器中
     **/
    @Override
    public Color getObject() throws Exception {
        System.out.println("ColorFactoryBean getObject...");
        return new Color();
    }

    /***
     * @return Bean的类型
     * @Author: wuhaohua
     * @Date: 2020/12/29
     * @Description: Bean的类型
     **/
    @Override
    public Class<?> getObjectType() {
        return Color.class;
    }

    /***
     * @return 若返回true，这个bean是单例的，在容器中只保存一份，若返回false，每次获取时都会创建一个新的bean
     * @Author: wuhaohua
     * @Date: 2020/12/29
     * @Description: 控制该bean是否是单实例
     **/
    @Override
    public boolean isSingleton() {
        return false;
    }
}
