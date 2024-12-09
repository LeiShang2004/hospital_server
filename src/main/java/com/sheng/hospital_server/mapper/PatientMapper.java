package com.sheng.hospital_server.mapper;

import com.sheng.hospital_server.pojo.Patient;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PatientMapper {
    void add(Patient patient);

    @Delete("delete from patient where patient_id = #{patientId}")
    void delete(Integer patientId);

    void update(Patient patient);

    @Select("select * from patient where patient_id = #{patientId}")
    Patient getById(Integer patientId);
//    List<Patient> getByUserId();

    @Select("select exists(select 1 from patient where patient_id = #{patientId})")
    Boolean existsById(Integer patientId);
}
