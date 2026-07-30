package org.example.tliaswebmanagement.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.example.tliaswebmanagement.mapper.EmpExprMapper;
import org.example.tliaswebmanagement.mapper.EmpMapper;
import org.example.tliaswebmanagement.pojo.*;
import org.example.tliaswebmanagement.service.EmpLogService;
import org.example.tliaswebmanagement.service.EmpService;
import org.example.tliaswebmanagement.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class EmpServicempl implements EmpService {

    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private EmpExprMapper empExprMapper;
    @Autowired
    private EmpLogService empLogService;


//    @Override
//    public PageResult<Emp> page(Integer page, Integer pageSize){
//
//        Long total=empMapper.count();
//
//        Integer start=(page-1)*pageSize;
//        List<Emp> rows=empMapper.list(start,pageSize);
//
//
//        return new PageResult<Emp>(total,rows);
//    }
    @Override
    public PageResult<Emp> page(Integer page, Integer pageSize, String name, Integer gender, LocalDate begin, LocalDate end){
    //1.设置分页参数
        PageHelper.startPage(page,pageSize);
    //2.执行查询
        List<Emp> empList=empMapper.list(name,gender,begin,end);
    //3.封装解析结果
        Page<Emp> p=(Page<Emp>)empList;
        return new PageResult<Emp>(p.getTotal(),p.getResult());//获取总数，数据
    }

    @Transactional(rollbackFor = {Exception.class})//所有异常都能触法回滚
    @Override
    public void save(Emp emp){
    try {
        //保存基本信息
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.insert(emp);

        //工作经历
        List<EmpExpr> exprList=emp.getExprList();
        if(!CollectionUtils.isEmpty(exprList)){

            exprList.forEach(empExpr -> {
                empExpr.setEmpId(emp.getId());
            });
            empExprMapper.insertBatch(exprList);
        }
    } finally {
        //记录操作日志
        EmpLog empLog=new EmpLog(null,LocalDateTime.now(),"新增员工"+emp);
        empLogService.insertLog(empLog);
        }
    }


    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void delete(List<Integer> ids) {
        //1.删除员工基本信息
        empMapper.deleteByIds(ids);

        //2.批量删除员工工作经历信息
        empExprMapper.deleteByEmpIds(ids);
    }

    @Override
    public Emp getInfo(Integer id) {
        return empMapper.getById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateById(emp);
        //根据id修改员工工作经历
        //先删除
        empExprMapper.deleteByEmpIds(Arrays.asList(emp.getId()));
        //再添加
        List<EmpExpr> exprList=emp.getExprList();
        if(!CollectionUtils.isEmpty(exprList)){
            exprList.forEach(empExpr -> empExpr.setEmpId(emp.getId()));
            empExprMapper.insertBatch(exprList);
        }

    }

    //查询所有员工

    @Override
    public List<Emp> list() {
        List<Emp> list=empMapper.listAll();
        return list;
    }

    //登录

    @Override
    public LoginInfo login(Emp emp) {
        //1.根据用户名密码查询信息
        Emp e=empMapper.selectByUsernameAndPassword(emp);

        //2.存在,组装信息
        //生成Jwt令牌
        Map<String,Object> claims=new HashMap<>();
        claims.put("id",e.getId());
        claims.put("username",e.getUsername());
        String jwt=JwtUtils.generateToken(claims);
        if(e!=null){
            return new LoginInfo(e.getId(),e.getUsername(),e.getName(),jwt);
        }

        //不存在，返回null
        return null;
    }
}
