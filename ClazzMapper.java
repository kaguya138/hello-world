package org.example.tliaswebmanagement.mapper;


import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.tliaswebmanagement.pojo.Clazz;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface ClazzMapper {
    List<Clazz> getClazz(String name, LocalDate begin, LocalDate end);

    void addClazz(Clazz clazz);

    @Select("select * from clazz where id=#{id}")
    Clazz getClazzById(Integer id);

    void updateClazz(Clazz clazz);

    @Delete("delete from clazz where id=#{id}")
    void deleteClazzById(Integer id);

    @Select("select * from clazz")
    List<Clazz> getAllClazz();
}
