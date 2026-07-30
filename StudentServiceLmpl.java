package org.example.tliaswebmanagement.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.example.tliaswebmanagement.mapper.StudentMapper;
import org.example.tliaswebmanagement.pojo.PageResult;
import org.example.tliaswebmanagement.pojo.Student;
import org.example.tliaswebmanagement.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class StudentServiceLmpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    //分页查询学生
    @Override
    public PageResult<Student> getStudent(String name, Integer degree, Integer clazzId, Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);

        List<Student> list= studentMapper.getStudent(name,degree,clazzId);

        Page<Student> p=(Page<Student>) list;
        return new PageResult<Student>(p.getTotal(),p.getResult());
    }

    //添加学生

    @Override
    public void addStudent(Student student) {
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());


        studentMapper.addStudent(student);
    }

    //根据ID查询学生

    @Override
    public Student getStudentById(Integer id) {
        Student student=studentMapper.getStudentById(id);
        return student;
    }

    //修改学生信息

    @Override
    public void updateStudent(Student student) {
        student.setUpdateTime(LocalDateTime.now());

        studentMapper.updateStudent(student);
    }

    //删除学生信息

    @Override
    public void deleteStudent(Integer id) {
        studentMapper.deleteStudent(id);
    }

    //违纪处理

    @Override
    public void violationSeal(Integer id, Integer score) {
        studentMapper.violationSeal(id,score);
    }
}
