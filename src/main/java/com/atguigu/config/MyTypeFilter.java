package com.atguigu.config;

import org.springframework.core.io.Resource;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;

/**
 * @author: wuhaohua
 * @date: Created in 2020/12/28 16:05
 * @description: 自定义包扫描过滤规则
 */
public class MyTypeFilter implements TypeFilter {
    /***
     * @param metadataReader 读取到的当前正在扫描的类的信息
     * @param metadataReaderFactory 获取到其他任何类（不止当前类）的信息
     * @return 若该类被过滤，则返回true，反之则返回false
     * @description 过滤所有类名中包含er的类
     **/
    @Override
    public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
        // 获取当前类注解的信息
        AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
        // 获取当前正在扫描的类的类信息，如类型、实现了什么接口等
        ClassMetadata classMetadata = metadataReader.getClassMetadata();
        // 获取当前类资源（类的路径等）
        Resource resource = metadataReader.getResource();
        // 获取类名（含包名）
        String className = classMetadata.getClassName();
        System.out.println("--->" + className);
        if (className.contains("er")) {
            return true;
        }
        return false;
    }
}
