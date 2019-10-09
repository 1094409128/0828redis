package com.aaa.zxz.redis.mapper;

import com.aaa.zxz.redis.model.Book;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BookMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Book record);

    int insertSelective(Book record);

    Book selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Book record);

    int updateByPrimaryKey(Book record);

    List<Book> selectAll();

    List<Book> selectIdBook(Integer id);

    int insertbooks(Book book);
}