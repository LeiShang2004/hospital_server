package com.sheng.hospital_server.mapper;

import com.sheng.hospital_server.pojo.Doctor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface DoctorMapper {
    @Select("select * from doctor where doctor_id = #{id}")
    public Doctor getById(Integer id);
}
