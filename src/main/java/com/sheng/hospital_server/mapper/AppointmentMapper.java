package com.sheng.hospital_server.mapper;

import com.sheng.hospital_server.pojo.Appointment;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface AppointmentMapper {


    void add(Appointment appointment);


    @Delete("delete from appointment where appointment_id = #{appointmentId}")
    void delete(Integer appointmentId);

    void update(Appointment appointment);

    @Update("update appointment set status = #{newStatus} where appointment_id = #{appointmentId}")
    void updateStatus(Integer appointmentId, Integer newStatus);

    Appointment getById(Integer appointmentId);

    List<Appointment> getByPatientId(Integer patientId);

    List<Appointment> getByDoctorId(Integer doctorId);

    List<Appointment> getByUserId(Integer userId);

    List<Appointment> getByScheduleId(Integer scheduleId);

    @Select("select * from appointment where schedule_id = #{scheduleId} and patient_id = #{patientId} and status = #{status}")
    Appointment getByScheduleIdAndPatientIdAndStatus(Integer scheduleId, Integer patientId, Integer status);

    @Select("select * from appointment")
    List<Appointment> getAll();

}
