package com.sheng.hospital_server.service.impl;


import com.sheng.hospital_server.mapper.PatientMapper;
import com.sheng.hospital_server.pojo.Patient;
import com.sheng.hospital_server.service.PatientService;
import com.sheng.hospital_server.utils.RSAUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {
    @Resource
    private PatientMapper patientMapper;

    @Override
    public void add(Patient patient) {
        if (!patient.getPlaintextId().isBlank()) {
            // RSA解密
            String cleartextId = RSAUtil.decrypt(patient.getPlaintextId());
            patient.setCleartextId(cleartextId);
            // MD5哈希
            if (cleartextId != null) {
                String hashedId = DigestUtils.md5DigestAsHex(cleartextId.getBytes());
                patient.setHashedId(hashedId);
            }
            // 明文身份证号 *代替后六位
            if (cleartextId != null) {
                patient.setCleartextId(cleartextId.substring(0, 12) + "******");
            }
        }
        patientMapper.add(patient);
    }

    @Override
    public void delete(Integer patientId) {
        patientMapper.delete(patientId);
    }

    @Override
    public void update(Patient patient) {
        patientMapper.update(patient);
    }

    @Override
    public Patient getById(Integer patientId) {
        return patientMapper.getById(patientId);
    }

    @Override
    public List<Patient> getByUserId() {

        return null;
    }
}
