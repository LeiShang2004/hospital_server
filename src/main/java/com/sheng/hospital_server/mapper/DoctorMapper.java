package com.sheng.hospital_server.mapper;

import com.sheng.hospital_server.pojo.Doctor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DoctorMapper {
    @Select("select * from doctor where doctor_id = #{id}")
    public Doctor getById(Integer id);

    @Select("select * from doctor where department_id = #{departmentId}")
    public List<Doctor> getByDepartmentId(Integer departmentId);

    @Select("select * from doctor where specialization_id = #{specializationId}")
    public List<Doctor> getBySpecializationId(Integer specializationId);

    @Select("select * from doctor")
    public List<Doctor> getAll();
}
