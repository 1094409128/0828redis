package com.aaa.zxz.redis.utils;

import com.aaa.zxz.redis.Enum.StatusEnum;
import com.aaa.zxz.redis.mapper.BookMapper;
import com.aaa.zxz.redis.model.Book;
import com.aaa.zxz.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ProjectName: 0828redis
 * @Package: com.aaa.zxz.redis.utils
 * @Author: zxz
 * @CreateDate: 2019/8/29 20:00
 * @Version: 1.0
 */
@Component
public class BookCRUDUtil {

    @Value("${book_key}")
    public String bookKey;
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private RedisService redisService;

    public  Map<String,Object> getBookCRUD(Integer i){
        Map<String, Object> map = new HashMap<String, Object>();
        if (i > 0 ){
            //查询新的图书信息
            List<Book> bookList = bookMapper.selectAll();
            //将图书信息添加到redis中
            String bookString = redisService.set(bookKey, JSONUtil.toJsonString(bookList));
            if (null != bookString && !"".equals(bookString)){
                map.put(StatusEnum.CORRECT.getCodeName(), StatusEnum.CORRECT.getTrueCode());
            }else {
                map.put(StatusEnum.CORRECT.getCodeName(),StatusEnum.CORRECT.getFalseCode());
            }
        }else {
            map.put(StatusEnum.CORRECT.getCodeName(), StatusEnum.CORRECT.getDisable());
        }

        return map;
    }
}
