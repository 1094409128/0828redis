package com.aaa.zxz.redis.controller;

import com.aaa.zxz.redis.Enum.StatusEnum;
import com.aaa.zxz.redis.model.Book;
import com.aaa.zxz.redis.service.BookService;
import com.aaa.zxz.redis.service.RedisService;
import com.sun.xml.internal.bind.v2.TODO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @ProjectName: 0828redis
 * @Package: com.aaa.zxz.redis.controller
 * @Author: zxz
 * @CreateDate: 2019/8/29 13:14
 * @Version: 1.0
 */
@Controller
public class BookController {

    @Autowired
    private BookService bookService;
    @Autowired
    private RedisService redisService;

    @RequestMapping("/")
    public String selectAllbook(ModelMap modelMap){
        Map<String, Object> bookMap = bookService.selectAllBook(redisService);
        int code =(Integer) bookMap.get(StatusEnum.CORRECT.getCodeName());
        List<Book> bookList = null;
        if (StatusEnum.CORRECT.getTrueCode() == code){
            bookList =(List<Book>) bookMap.get(StatusEnum.CORRECT.getResult());
            modelMap.addAttribute("bookList",bookList);
        }else if (StatusEnum.CORRECT.getFalseCode() == code){
            if(null == bookList){
                System.out.println("说明mysql出错了");
            }else {
                System.out.println("说明redis出错了");
                return "404";
            }
        }else {
            // TODO ;
        }
        return "book_index";
    }

    @RequestMapping("/bookInsert")
    public String bookInsert(){
        return "book_insert";
    }

    /**
    
     * @author     zxz
     * @method    添加
     * @see       
     * @return    
     * @exception 
     * @date      2019/8/29 20:55
     */
    @RequestMapping("/insertBook")
    public String insertBook(Book book){
        Map<String, Object> map = bookService.insertBook(book);
        return returnBookPage("redirect:/",map);
    }

    /**

     * @author     zxz
     * @method    删除
     * @see
     * @return
     * @exception
     * @date      2019/8/29 20:56
     */
    @RequestMapping("/deleteBook")
    public String deleteBook(Integer id){
        Map<String, Object> map = bookService.deleteBook(id);
        return returnBookPage("redirect:/",map);
    }

    @RequestMapping("/selectUpdateBook")
    public String selectUpdateBook(Integer id,ModelMap modelMap){
        Map<String, Object> map = bookService.selectIdBook(id, redisService);
        int code =(Integer) map.get(StatusEnum.CORRECT.getCodeName());
        if(StatusEnum.CORRECT.getTrueCode() == code){
            List<Book> bookList =(List<Book>) map.get(StatusEnum.CORRECT.getResult());
            modelMap.addAttribute("bookList",bookList);
            return "book_update";
        }
        return "404";
    }

    /**
     * @author     zxz
     * @method    修改
     * @see
     * @return
     * @exception
     * @date      2019/8/29 21:00
     */
    @RequestMapping("/updateBook")
    public String updateBook(Book book){
        Map<String, Object> map = bookService.updateBook(book);
        return returnBookPage("redirect:/",map);
    }





    private String returnBookPage(String index,Map<String, Object> map){
        int code =(Integer) map.get(StatusEnum.CORRECT.getCodeName());
        if(StatusEnum.CORRECT.getTrueCode() == code){
            return index;
        }
        return "404";
    }


}
