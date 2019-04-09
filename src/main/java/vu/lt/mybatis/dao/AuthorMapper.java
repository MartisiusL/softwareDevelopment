package vu.lt.mybatis.dao;

import org.mybatis.cdi.Mapper;
import vu.lt.mybatis.model.Author;

import java.util.List;


@Mapper
public interface AuthorMapper {

    int deleteByPrimaryKey(Integer id);
    int insert(Author record);
    Author selectByPrimaryKey(Integer id);
    List<Author> selectAll();
    int updateByPrimaryKey(Author record);
}
