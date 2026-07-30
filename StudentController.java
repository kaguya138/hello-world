package org.example.tliaswebmanagement.controller;


import lombok.extern.slf4j.Slf4j;
import org.example.tliaswebmanagement.pojo.PageResult;
import org.example.tliaswebmanagement.pojo.Result;
import org.example.tliaswebmanagement.pojo.Student;
import org.example.tliaswebmanagement.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class StudentController {

    @Autowired
    private StudentService studentService;


    //分页查询显示学生
    @GetMapping("/students")
    public Result getStudent(String name, Integer degree, Integer clazzId,
                             @RequestParam(defaultValue = "1")Integer page,
                             @RequestParam(defaultValue = "10")Integer pageSize){
       log.info("接收到的参数为：{}，{}，{}，{}，{}",name,degree,clazzId,page,pageSize);
       PageResult<Student> pageResult= studentService.getStudent(name,degree,clazzId,page,pageSize);
       return Result.success(pageResult);

    }

    //新增学生
    @PostMapping("/students")
    public Result addStudent(@RequestBody Student student){
        log.info("添加学生成功啦");
        studentService.addStudent(student);
        return Result.success();
    }

    //根据id查询学生信息
    @GetMapping("/students/{id}")
    public Result GetStudentById(@PathVariable Integer id){
        log.info("查询成功啦：{}",id);
        Student student=studentService.getStudentById(id);
        return Result.success(student);
    }

    //修改学生
    @PutMapping("/students")
    public Result updateStudent(@RequestBody Student student){
        log.info("要修改为：{}",student);
        studentService.updateStudent(student);
        return Result.success();
    }

    //删除学生
    @DeleteMapping("/students/{id}")
    public Result deleteStudent(@PathVariable Integer id){
        log.info("删除成功啦，{}",id);
        studentService.deleteStudent(id);
        return Result.success();
    }

    //违纪处理
    @PutMapping("/students/violation/{id}/{score}")
    public Result violationSeal(@PathVariable Integer id,@PathVariable Integer score){
        log.info("{}学生犯错力（悲），扣除{}分",id,score);
        studentService.violationSeal(id,score);
        return Result.success();
    }
}
