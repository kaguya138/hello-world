package org.example.tliaswebmanagement.service;

import org.example.tliaswebmanagement.pojo.PageResult;
import org.example.tliaswebmanagement.pojo.Student;

public interface StudentService {
    PageResult<Student> getStudent(String name, Integer degree, Integer clazzId, Integer page, Integer pageSize);

    void addStudent(Student student);

    Student getStudentById(Integer id);

    void updateStudent(Student student);

    void deleteStudent(Integer id);

    void violationSeal(Integer id, Integer score);
}
