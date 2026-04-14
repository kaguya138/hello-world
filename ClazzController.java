package org.example.tliaswebmanagement.controller;


import lombok.extern.slf4j.Slf4j;
import org.example.tliaswebmanagement.pojo.Clazz;
import org.example.tliaswebmanagement.pojo.PageResult;
import org.example.tliaswebmanagement.pojo.Result;
import org.example.tliaswebmanagement.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@Slf4j
public class ClazzController {


    @Autowired
    private ClazzService clazzService;

    //查询班级列表,分页
    @GetMapping("/clazzs")
    public Result getClazz(String name,
                           @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate begin,
                           @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate end,
                           @RequestParam(defaultValue = "1") Integer page,
                           @RequestParam(defaultValue = "10") Integer pageSize
                           ){
        log.info("接收到的参数为：{}，{}，{}，{}，{}",name,begin,end,page,pageSize);
        PageResult<Clazz> pageResult = clazzService.getClazz(name,begin,end,page,pageSize);
        return Result.success(pageResult);
    }



    //新增班级
    @PostMapping("/clazzs")
    public Result addClazz(@RequestBody Clazz clazz){
        log.info("添加成功啦,{}",clazz);
        clazzService.addClazz(clazz);
        return Result.success();
    }

    //根据id查询回显
    @GetMapping("/clazzs/{id}")
    public Result getClassById(@PathVariable Integer id){
        log.info("查询成功啦");
        Clazz clazz=clazzService.getClazzById(id);
        return Result.success(clazz);
    }

    //修改班级信息
    @PutMapping("/clazzs")
    public Result updateClazz(@RequestBody Clazz clazz){
        log.info("修改成功啦");
        clazzService.updateClazz(clazz);
        return Result.success();
    }

    //删除班级信息
    @DeleteMapping("/clazzs/{id}")
    public Result deleteClazzById(@PathVariable Integer id){
        log.info("删除成功啦");
        clazzService.deleteClazzById(id);
        return Result.success();
    }

    //查询所有班级
    @GetMapping("/clazzs/list")
    public Result getAllClazz(){
        log.info("查询成功啦");
        List<Clazz> list=clazzService.getAllClazz();
        return Result.success(list);
    }


}
