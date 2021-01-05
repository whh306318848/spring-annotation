package com.atguigu.controller;

import com.atguigu.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @author: wuhaohua
 * @date: Created in 2020/12/28 13:44
 * @description: TODO
 */
@Controller
public class BookController {

    @Autowired
    private BookService bookService;
}
