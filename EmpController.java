package org.example.tliaswebmanagement.controller;


import lombok.extern.slf4j.Slf4j;
import org.example.tliaswebmanagement.anno.Log;
import org.example.tliaswebmanagement.mapper.EmpExprMapper;
import org.example.tliaswebmanagement.pojo.Emp;
import org.example.tliaswebmanagement.pojo.EmpExpr;
import org.example.tliaswebmanagement.pojo.PageResult;
import org.example.tliaswebmanagement.pojo.Result;
import org.example.tliaswebmanagement.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/emps")
public class EmpController {

    @Autowired
    private EmpService empService;



    @GetMapping
    public Result page(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "0")Integer pageSize,
                       String name, Integer gender, @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate begin,@DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate end){
        log.info("分页查询：{}，{},{},{},{},{}",page,pageSize,name,gender,begin,end);
        PageResult<Emp> pageResult=empService.page(page,pageSize,name,gender,begin,end);
        return Result.success(pageResult);

    }

    //新增员工
    @PostMapping
    @Log
    public Result save(@RequestBody Emp emp){
        log.info("{}",emp);
        empService.save(emp);
        return Result.success();


    }

//    @DeleteMapping
//    public Result delete(Integer[] ids){
//        log.info("删除员工：{}", Arrays.toString(ids));
//        return Result.success();
//    }//接收多个参数，数组接收


     //批量删除员工
    @DeleteMapping
    @Log
    public Result delete(@RequestParam List<Integer> ids){
        log.info("删除员工：{}", ids);
        empService.delete(ids);
        return Result.success();
    }//保存到集合中

    //根据id查询员工信息
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){
        log.info("id是，{}",id);
        Emp emp=empService.getInfo(id);
        return Result.success(emp);
    }

    @PutMapping
    @Log
    public Result update(@RequestBody Emp emp){
        log.info("修改员工：{}",emp);
        empService.update(emp);
        return Result.success();
    }

    //查询全部员工
    @GetMapping("/list")
    public Result list(){
        log.info("查询成功啦");
        List<Emp> list=empService.list();
        return Result.success(list);
    }

}
