package com.sheng.hospital_server.service;

import com.sheng.hospital_server.pojo.Appointment;

import java.util.List;

public interface AppointmentService {
    void add(Appointment appointment);

    void delete(Integer appointmentId);

    void update(Appointment appointment);

    void updateStatus(Integer appointmentId, Integer newStatus);

    Appointment getById(Integer appointmentId);

    List<Appointment> getByPatientId(Integer patientId);

    List<Appointment> getByDoctorId(Integer doctorId);

    List<Appointment> getByUserId(Integer userId);
}
