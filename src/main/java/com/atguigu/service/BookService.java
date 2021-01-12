package com.atguigu.service;

import com.atguigu.dao.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.inject.Inject;

/**
 * @author: wuhaohua
 * @date: Created in 2020/12/28 14:08
 * @description: TODO
 */
@Service
public class BookService {

//    @Qualifier("bookDao")
//    @Autowired(required = false)
//    @Resource(name = "bookDao2")
    @Inject
    private BookDao bookDao;

    public void print() {
        System.out.println(this.bookDao);
    }

    @Override
    public String toString() {
        return "BookService{" +
                "bookDao=" + bookDao +
                '}';
    }
}
