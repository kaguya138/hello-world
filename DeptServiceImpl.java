package org.example.tliaswebmanagement.service.impl;

import org.example.tliaswebmanagement.exceptions.CannotDeleteException;
import org.example.tliaswebmanagement.mapper.DeptMapper;
import org.example.tliaswebmanagement.mapper.EmpMapper;
import org.example.tliaswebmanagement.pojo.Dept;
import org.example.tliaswebmanagement.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service//业务逻辑层使用的IOS注解，本质和component一样，为了区分要这么写
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    private EmpMapper empMapper;

    //查询显示所有部门
    @Override
    public List<Dept> findAll() {
        return deptMapper.findAll();
    }

    //删除部门
    @Override
    public void deleteById(Integer id){

        Integer count=empMapper.countEmp(id);
        if(count>0){
            throw new CannotDeleteException("有人你也删吗！");
        }

        deptMapper.deleteById(id);
    }

    @Override
    public void add(Dept dept){
        //1.补全基础属性createtime，updatetime
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());

        //2.调用mapper接口
        deptMapper.insert(dept);
    }

    //根据id对部门进行查询回显
    @Override
    public Dept getById(Integer id){
        return deptMapper.getById(id);
    }

    @Override
    public void update(Dept dept){
        //1.补全基础属性
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.update(dept);
    }
}
