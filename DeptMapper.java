package org.example.tliaswebmanagement.mapper;

import org.apache.ibatis.annotations.*;
import org.example.tliaswebmanagement.pojo.Dept;

import java.util.List;

@Mapper
public interface DeptMapper {

//    @Results({
//            @Result(column = "create_time", property = "createTime"),
//            @Result(column = "update_time", property = "updateTime")
//    })//方式一：数据库中名字与定义的对象属性名不一样不会封装，需要手动封装

    @Select("select id,name,create_time,update_time from dept order by update_time desc")

//    @Select("select id,name,create_time createTime,update_time updateTime from dept order by update_time desc")//方式二：起别名方式
    List<Dept> findAll();


    @Delete("delete from dept where id=#{id}")
    void deleteById(Integer id);

    @Insert("Insert into dept(name,create_time,update_time) values (#{name},#{createTime},#{updateTime})")
    void insert(Dept dept);

    @Select("select id,name,create_time,update_time from dept where id=#{id}")
    Dept getById(Integer id);

    @Update("update dept set name=#{name},update_time=#{updateTime} where id=#{id}")
    void update(Dept dept);


}
