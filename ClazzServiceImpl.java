package org.example.tliaswebmanagement.service.impl;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.example.tliaswebmanagement.exceptions.CannotDeleteException;
import org.example.tliaswebmanagement.mapper.ClazzMapper;
import org.example.tliaswebmanagement.mapper.StudentMapper;
import org.example.tliaswebmanagement.pojo.Clazz;
import org.example.tliaswebmanagement.pojo.PageResult;
import org.example.tliaswebmanagement.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService {

    @Autowired
    private ClazzMapper clazzMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public PageResult<Clazz> getClazz(String name, LocalDate begin, LocalDate end, Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);

        List<Clazz> list=clazzMapper.getClazz(name,begin,end);
        for(Clazz c:list){
            LocalDate b= c.getBeginDate();
            LocalDate e= c.getEndDate();
            if(b.isAfter(LocalDate.now())){
                c.setStatus("未开课");
            }
            else if(e.isBefore(LocalDate.now())){
                c.setStatus("已结课");
            }
            else{
                c.setStatus("在读中");
            }
        }

        Page<Clazz> p=(Page<Clazz>) list;
        return new PageResult<Clazz>(p.getTotal(),p.getResult());
    }

    //新增班级

    @Override
    public void addClazz(Clazz clazz) {
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());

        clazzMapper.addClazz(clazz);
    }

    //根据id查询回显

    @Override
    public Clazz getClazzById(Integer id) {
        return clazzMapper.getClazzById(id);
    }

    //修改班级信息

    @Override
    public void updateClazz(Clazz clazz) {
        clazz.setUpdateTime(LocalDateTime.now());

        clazzMapper.updateClazz(clazz);
    }

    //删除班级信息

    @Override
    public void deleteClazzById(Integer id) {

        int count=studentMapper.countById(id);
        if(id>0){
            throw new CannotDeleteException("有人你还删吗？？");
        }//判断班里是否有人，有人的话不能删除

        clazzMapper.deleteClazzById(id);
    }

    //查询所有班级信息

    @Override
    public List<Clazz> getAllClazz() {
        List<Clazz> list=clazzMapper.getAllClazz();
        return list;
    }
}
