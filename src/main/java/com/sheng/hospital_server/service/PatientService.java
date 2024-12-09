package com.sheng.hospital_server.service;

import com.sheng.hospital_server.pojo.Patient;

import java.util.List;

public interface PatientService {
    void add(Patient patient);

    void delete(Integer patientId);

    void update(Patient patient);

    Patient getById(Integer patientId);

    Boolean existsById(Integer id);

    List<Patient> getByUserId();
}
