package com.atguigu.condition;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author: wuhaohua
 * @date: Created in 2020/12/28 19:59
 * @description: 自定义逻辑返回需要导入的组件
 */
public class MyImportSelector implements ImportSelector {
    /***
     * @param annotationMetadata 当前标注@Import注解的类的所有信息
     * @return 要导入到容器中的组件全类名
     * @throws
     * @Author: wuhaohua
     * @Date: 2020/12/28
     * @Description:
     **/
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {

        //方法不要返回null值，否则会报空指针异常
        return new String[]{"com.atguigu.bean.Blue", "com.atguigu.bean.Yellow"};
    }
}
