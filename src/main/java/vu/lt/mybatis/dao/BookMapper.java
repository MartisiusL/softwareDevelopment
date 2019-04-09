package vu.lt.mybatis.dao;

import java.util.List;


import org.mybatis.cdi.Mapper;
import vu.lt.mybatis.model.Book;


@Mapper
public interface BookMapper {

    int deleteByPrimaryKey(Integer id);
    int insert(Book record);
    Book selectByPrimaryKey(Integer id);
    List<Book> selectAll();
    int updateByPrimaryKey(Book record);
    List<Book> selectByLibraryId(Integer id);

}