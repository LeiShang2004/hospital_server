package com.sheng.hospital_server.mapper;

import com.sheng.hospital_server.pojo.Doctor;
import org.apache.ibatis.annotations.*;

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

    @Select("select * from doctor where name like  CONCAT('%',#{name}, '%')")
    List<Doctor> getByName(String name);

    @Select("select * from doctor where introduction like CONCAT('%',#{introduction}, '%')")
    List<Doctor> getByIntroduction(String introduction);

    @Insert("insert into doctor(name, gender,department_id, specialization_id, title_id,photo_url,created_time,updated_time,introduction) values(#{name}, #{gender}, #{departmentId},#{specializationId}, #{titleId}, #{photoUrl}, NOW(), NOW(),#{introduction})")
    @Options(useGeneratedKeys = true, keyProperty = "doctorId")
    void add(Doctor doctor);

    @Delete("delete from doctor where doctor_id = #{id}")
    void deleteById(Integer id);

    @Update("update doctor set name = #{name} ,gender = #{gender},department_id = #{departmentId},specialization_id = #{specializationId},title_id = #{titleId},photo_url = #{photoUrl},updated_time = NOW(),introduction = #{introduction} where doctor_id = #{doctorId}")
    void update(Doctor doctor);
}
