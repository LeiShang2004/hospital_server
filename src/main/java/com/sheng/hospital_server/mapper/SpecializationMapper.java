package com.sheng.hospital_server.mapper;


import com.sheng.hospital_server.pojo.Specialization;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SpecializationMapper {
    @Insert("insert into specialization(name) values(#{specialization.name})")
    void add(Specialization specialization);

    @Delete("delete from specialization where specialization_id=#{id}")
    void delete(Integer id);

    @Update("update specialization set name=#{specialization.name}, department_id=#{specialization.departmentId} where specialization_id=#{specialization.specializationId}")
    void update(Specialization specialization);

    @Select("select * from specialization where specialization_id=#{id}")
    Specialization getById(Integer id);

    @Select("select * from specialization")
    List<Specialization> getAll();

    @Select("select * from specialization where department_id=#{departmentId}")
    List<Specialization> getByDepartmentId(Integer departmentId);

}
