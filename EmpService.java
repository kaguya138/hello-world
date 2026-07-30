package org.example.tliaswebmanagement.service;

import org.example.tliaswebmanagement.pojo.Emp;
import org.example.tliaswebmanagement.pojo.LoginInfo;
import org.example.tliaswebmanagement.pojo.PageResult;

import java.time.LocalDate;
import java.util.List;


public interface EmpService {

    PageResult<Emp> page(Integer page, Integer pageSize, String name, Integer gender,LocalDate begin, LocalDate end);


    void save(Emp emp);

    void delete(List<Integer> ids);

    Emp getInfo(Integer id);

    void update(Emp emp);

    List<Emp> list();

    LoginInfo login(Emp emp);
}
