package com.sheng.hospital_server.mapper;

import com.sheng.hospital_server.pojo.Doctor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DoctorMapper {
    @Select("select * from doctor where doctor_id = #{id}")
    Doctor getById(Integer id);

    @Select("select * from doctor where department_id = #{departmentId}")
    List<Doctor> getByDepartmentId(Integer departmentId);

    @Select("select * from doctor where specialization_id = #{specializationId}")
    List<Doctor> getBySpecializationId(Integer specializationId);

    @Select("select * from doctor")
    List<Doctor> getAll();

    @Select("select exists(select 1 from doctor where doctor_id = #{id})")
    Boolean existsById(Integer id);
}
