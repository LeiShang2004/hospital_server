package com.sheng.hospital_server.service;

import com.sheng.hospital_server.pojo.Appointment;

import java.util.List;

public interface AppointmentService {

    public static final Integer STATUS_PENDING_CONFIRMATION = 1;
    public static final Integer STATUS_CONFIRMED = 2;
    public static final Integer STATUS_CANCELLED = 3;
    public static final Integer STATUS_COMPLETED = 4;


    Integer add(Appointment appointment);

    void cancel(Integer appointmentId);

    void delete(Integer appointmentId);

    void update(Appointment appointment);

    void updateStatus(Integer appointmentId, Integer newStatus);

    Appointment getById(Integer appointmentId);

    List<Appointment> getByPatientId(Integer patientId);

    List<Appointment> getByDoctorId(Integer doctorId);

    List<Appointment> getByUserId(Integer userId);
}
