package com.sheng.hospital_server.mapper;

import com.sheng.hospital_server.pojo.Department;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DepartmentMapper {
    @Select("select * from department")
    public List<Department> getAll();

    @Select("select * from department where department_id = #{id}")
    public Department getById(Integer id);

    @Select("select * from department where name = #{name}")
    public Department getByName(String name);
}
