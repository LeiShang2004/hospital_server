package com.sheng.hospital_server.mapper;

import com.sheng.hospital_server.pojo.Patient;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
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

    // 通过用户id获取患者信息
    @Select("select * from patient where patient_id in (select patient_id from user_patient where user_id = #{userId})")
    List<Patient> getByUserId(Integer userId);

    // 新增用户患者关系
    @Insert("insert into user_patient(user_id, patient_id) values(#{userId}, #{patientId})")
    void addUserPatient(Integer userId, Integer patientId);

    // 通过患者身份证号获取患者id
    @Select("select patient_id from patient where hashed_id = #{hashedId} and name = #{name}")
    Integer getIdByNameAndHashedId(String name, String hashedId);
}
