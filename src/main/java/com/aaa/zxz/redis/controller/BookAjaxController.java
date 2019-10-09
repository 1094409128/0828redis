package com.aaa.zxz.redis.controller;

import com.aaa.zxz.redis.Enum.StatusEnum;
import com.aaa.zxz.redis.model.Book;
import com.aaa.zxz.redis.service.BookService;
import com.aaa.zxz.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @ProjectName: 0828redis
 * @Package: com.aaa.zxz.redis.controller
 * @Author: zxz
 * @CreateDate: 2019/8/30 14:25
 * @Version: 1.0
 */
@Controller
public class BookAjaxController {
    @Autowired
    private BookService bookService;
    @Autowired
    private RedisService redisService;
    @RequestMapping("/book_ajax")
    public String book_ajax(){
        return "book_ajax";
    }

    @GetMapping("/bookAddAjax")
    @ResponseBody
    public Map<String,Object> bookListAjax(){
        return bookService.selectAllBook(redisService);
    }


    @RequestMapping("/addBooks")
    @ResponseBody
    public Map<String,Object> addBooks(Book book){
        return bookService.insertbooks(book);
    }

    @DeleteMapping("{id}")
    @ResponseBody
    public Map<String,Object> deleteBooks(@PathVariable("id") Integer id){
        return bookService.deleteBook(id);
    }
}
