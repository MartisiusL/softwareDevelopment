package vu.lt.mybatis.dao;

import java.util.List;

import org.mybatis.cdi.Mapper;
import vu.lt.mybatis.model.Library;


@Mapper
public interface LibraryMapper {

    int deleteByPrimaryKey(Integer id);
    int insert(Library record);
    Library selectByPrimaryKey(Integer id);
    List<Library> selectAll();
    int updateByPrimaryKey(Library record);
}
