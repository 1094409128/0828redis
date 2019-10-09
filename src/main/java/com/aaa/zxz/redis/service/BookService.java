package com.aaa.zxz.redis.service;

import com.aaa.zxz.redis.Enum.StatusEnum;
import com.aaa.zxz.redis.mapper.BookMapper;
import com.aaa.zxz.redis.model.Book;
import com.aaa.zxz.redis.utils.BookCRUDUtil;
import com.aaa.zxz.redis.utils.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ProjectName: 0828redis
 * @Package: com.aaa.zxz.redis.service
 * @Author: zxz
 * @CreateDate: 2019/8/28 20:03
 * @Version: 1.0
 */
@Service
public class BookService {

    @Value("${book_key}")
    public String bookKey;
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private BookCRUDUtil bookCRUDUtil;

    public Map<String,Object> selectAllBook(RedisService redisService){
        Map<String,Object> map = new HashMap<String, Object>();
        //从redis中查询所有图书信息
        String bookString = redisService.get(bookKey);
        //判断redis中是否有数据
        if(null == bookString || "".equals(bookString)){
            //说明redis中没有数据
            //向数据库中查询
            List<Book> bookList = bookMapper.selectAll();
            if(bookList.size()>0){
                // 说明数据库中有数据
                // 5.把数据存入到redis缓存库中
                try {
                    redisService.set(bookKey, JSONUtil.toJsonString(bookList));
                    map.put(StatusEnum.CORRECT.getCodeName(), StatusEnum.CORRECT.getTrueCode());
                    map.put(StatusEnum.CORRECT.getResult(), bookList);
                }catch (Exception e){
                    // 如果进入到catch(项目出现运行时异常)，在catch中是需要处理数据的
                    redisService.set(bookKey, JSONUtil.toJsonString(bookList));
                    // 一旦检测code值为404的时候，说明mysql中没有问题，redis抛异常了！
                    map.put(StatusEnum.CORRECT.getCodeName(), StatusEnum.CORRECT.getFalseCode());
                    map.put(StatusEnum.CORRECT.getResult(), bookList);
                    e.printStackTrace();
                    return map;
                }
            }else {
                //说明数据库没有数据
                map.put(StatusEnum.CORRECT.getCodeName(), StatusEnum.CORRECT.getFalseCode());
                map.put(StatusEnum.CORRECT.getResult(),bookList);
            }
        }else {
            map.put(StatusEnum.CORRECT.getCodeName(),StatusEnum.CORRECT.getTrueCode());
            map.put(StatusEnum.CORRECT.getResult(),JSONUtil.toList(bookString,Book.class) );
        }

        return map;
    }

    /**
    
     * @author     zxz
     * @method    图书的添加方法
     * @see       
     * @return    
     * @exception 
     * @date      2019/8/29 19:57
     */
    public Map<String, Object> insertBook(Book book){
            //调用添加图书方法
            int i = bookMapper.insertSelective(book);
            return bookCRUDUtil.getBookCRUD(i);
    }


    /**
    
     * @author     zxz
     * @method    删除的方法
     * @see       
     * @return    
     * @exception 
     * @date      2019/8/29 20:55
     */
    public Map<String, Object> deleteBook(Integer id){
        int i = bookMapper.deleteByPrimaryKey(id);
        return bookCRUDUtil.getBookCRUD(i);
    }
    /**

     * @author     zxz
     * @method    根据id查询图书信息
     * @see
     * @return
     * @exception
     * @date      2019/8/29 21:05
     */
    public Map<String, Object> selectIdBook(Integer id,RedisService redisService){
        Map<String,Object> map = new HashMap<String, Object>();
        List<Book> bookList = bookMapper.selectIdBook(id);
        if(bookList.size() > 0){
            map.put(StatusEnum.CORRECT.getCodeName(), StatusEnum.CORRECT.getTrueCode());
            map.put(StatusEnum.CORRECT.getResult(), bookList);
        }else {
            map.put(StatusEnum.CORRECT.getCodeName(),StatusEnum.CORRECT.getFalseCode());
        }
        return map;
    }
    
    /**
    
     * @author     zxz
     * @method    修改
     * @see       
     * @return    
     * @exception 
     * @date      2019/8/29 21:43
     */
    public Map<String, Object> updateBook(Book book){
            int i = bookMapper.updateByPrimaryKey(book);
            return bookCRUDUtil.getBookCRUD(i);
    }


    public Map<String, Object> insertbooks(Book book){
        int i = bookMapper.insertbooks(book);
        return bookCRUDUtil.getBookCRUD(i);
    }
}
