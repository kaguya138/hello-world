package org.example.tliaswebmanagement.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.example.tliaswebmanagement.anno.Log;
import org.example.tliaswebmanagement.pojo.Dept;
import org.example.tliaswebmanagement.pojo.Result;
import org.example.tliaswebmanagement.service.DeptService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j

public class DeptController {

    //private static final Logger log= LoggerFactory.getLogger(DeptController.class);

    @Autowired
    private DeptService deptService;

    //@RequestMapping(value = "/depts",method = RequestMethod.GET)

    //查询显示所有部门
    @GetMapping("/depts")
    public Result list(){
        log.info("查询成功啦");
        List<Dept> deptList=deptService.findAll();
        return Result.success(deptList);
    }

    //删除部门
    @DeleteMapping("/depts")
    @Log
    public Result delete(Integer id){
        //System.out.println("删除部门数据"+id);
        log.info("删除部门:{}",id);
        deptService.deleteById(id);
        return Result.success();
    }

    //新增部门
    @PostMapping("/depts")
    @Log
    public Result add(@RequestBody Dept dept){
        //System.out.println(dept);
        log.info("新增部门：{}",dept);
        deptService.add(dept);
        return Result.success();
    }
//    public Result delete(HttpServletRequest request){
//        String idStr=request.getParameter("id");
//        int id=Integer.parseInt(idStr);
//        System.out.println("删除部门数据"+id);
//        return Result.success();
//    }

    //根据id查询部门
    @GetMapping("/depts/{id}")
    public Result getInfo(@PathVariable("id") Integer deptId){
        //System.out.println(deptId);
        log.info("根据id查询部门：{}",deptId);
        Dept dept=deptService.getById(deptId);
        return Result.success(dept);
    }

    @PutMapping("/depts")
    @Log
    public Result update(@RequestBody Dept dept){
        //System.out.println(dept);
        log.info("修改部门:{}",dept);
        deptService.update(dept);
        return Result.success();
    }


}
