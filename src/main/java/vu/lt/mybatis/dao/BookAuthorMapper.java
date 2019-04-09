package vu.lt.mybatis.dao;

import org.apache.ibatis.annotations.Param;
import org.mybatis.cdi.Mapper;
import vu.lt.mybatis.model.BookAuthor;

import java.util.List;

@Mapper
public interface BookAuthorMapper {

    int deleteByPrimaryKey(@Param("bookId") Integer bookId, @Param("authorId") Integer authorId);
    int insert(BookAuthor record);
    List<BookAuthor> selectAll();
}
