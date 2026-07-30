package org.example.tliaswebmanagement.mapper;


import org.apache.ibatis.annotations.*;
import org.example.tliaswebmanagement.pojo.Emp;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface EmpMapper {


//原始分页查询
    //查询总记录数
//    @Select("select count(*) from emp e left join  dept d on e.dept_id=d.id")
//    public Long count();
//
//    @Select("select e.*,d.name deptName from emp e left join dept d on e.dept_id = d.id order by e.update_time desc limit #{start},#{pageSize} ")
//    public List<Emp> list(Integer start,Integer pageSize);


    //@Select("select e.*,d.name deptName from emp e left join dept d on e.dept_id = d.id where e.name like '%阮%' and e.gender=1 and e.entry_date between '2010-01-01' and '2020-01-01' order by e.update_time desc")

    public List<Emp> list(String name, Integer gender, LocalDate begin,LocalDate end);


//    option的作用：把数据库自动生成的主键 ID，回填到刚 save 的那个 Java 对象的 id 字段里。
    @Options(useGeneratedKeys = true,keyProperty = "id")//获取到生成的主键--mybatis主键返回，调用insert时把id赋值给emp.id
    @Insert("insert into emp(username, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time) VALUES" +
            " (#{username},#{name},#{gender},#{phone},#{job},#{salary},#{image},#{entryDate},#{deptId},#{createTime},#{updateTime})")
    void insert(Emp emp);

    void deleteByIds(List<Integer> ids);

    Emp getById(Integer id);

    void updateById(Emp emp);

    @MapKey("pos")
    List<Map<String,Object>> countEmpJobData();

    @MapKey("gender")
    List<Map<String,Object>> countEmpGenderData();

    @Select("select id,username,password,name,gender,phone,image,job,salary,entry_date,dept_id,create_time,update_time from emp")
    List<Emp> listAll();

    @Select("select count(*) from emp where dept_id=#{id}")
    Integer countEmp(Integer id);


    @Select("select id,username,name from emp where username=#{username} and password=#{password}")
    Emp selectByUsernameAndPassword(Emp emp);
}
