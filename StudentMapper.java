package org.example.tliaswebmanagement.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.tliaswebmanagement.pojo.Student;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {
    List<Student> getStudent(String name, Integer degree, Integer clazzId);

    @Select("select count(*) from student where clazz_id=#{id}")
    int countById(Integer id);


    void addStudent(Student student);

    @Select("select * from student where id=#{id}")
    Student getStudentById(Integer id);

    void updateStudent(Student student);

    @Delete("delete from student where id=#{id}")
    void deleteStudent(Integer id);


    void violationSeal(Integer id, Integer score);

    @MapKey("degree")
    List<Map<String, Object>> getStudentDegreeData();

    List<Map<String, Object>> getStudentCountData();
}
