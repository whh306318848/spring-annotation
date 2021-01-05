package com.atguigu.dao;

import org.springframework.stereotype.Repository;

/**
 * @author: wuhaohua
 * @date: Created in 2020/12/28 14:09
 * @description: TODO
 */
// 注入的Bean，名字默认是首字母小写
@Repository
public class BookDao {

    private String lable = "1";

    public String getLable() {
        return lable;
    }

    public void setLable(String lable) {
        this.lable = lable;
    }

    @Override
    public String toString() {
        return "BookDao{" +
                "lable='" + lable + '\'' +
                '}';
    }
}
