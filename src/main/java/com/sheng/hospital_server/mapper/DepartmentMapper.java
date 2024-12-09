package com.sheng.hospital_server.mapper;

import com.sheng.hospital_server.pojo.Department;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface  DepartmentMapper {
    @Select("select * from department")
    List<Department> getAll();

    @Select("select * from department where department_id = #{id}")
    Department getById(Integer id);

    @Insert("insert into department(name) values(#{department.name})")
    void add(Department department);

    @Delete("delete from department where department_id = #{department.departmentId}")
    void delete(Integer id);

    @Update("update department set name = #{department.name} where department_id = #{department.departmentId}")
    void update(Department department);
}
