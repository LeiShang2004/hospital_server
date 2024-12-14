package com.sheng.hospital_server.service;

import com.sheng.hospital_server.pojo.Appointment;
import com.sheng.hospital_server.pojo.AppointmentInfo;

import java.util.List;

public interface AppointmentService {

    // 待确认
    Integer STATUS_PENDING_CONFIRMATION = 1;
    // 已确认
    Integer STATUS_CONFIRMED = 2;
    // 已取消
    Integer STATUS_CANCELLED = 3;
    // 已完成
    Integer STATUS_COMPLETED = 4;


    void add(Appointment appointment);

    void cancel(Integer appointmentId);

    void cancelPayment(Integer appointmentId);

    void confirmPayment(Integer appointmentId);

    void delete(Integer appointmentId);

    void update(Appointment appointment);

    void updateStatus(Integer appointmentId, Integer newStatus);

    Appointment getById(Integer appointmentId);

    List<Appointment> getByPatientId(Integer patientId);

    List<Appointment> getByDoctorId(Integer doctorId);

    List<Appointment> getByUserId(Integer userId);

    List<AppointmentInfo> getAll();
}
