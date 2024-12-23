package com.sheng.hospital_server.service.impl;


import cn.dev33.satoken.stp.StpUtil;
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
    public Patient add(Patient patient) {
        Integer patientId = patientMapper.getIdByNameAndHashedId(patient.getName(), patient.getHashedId());
        // 如果数据库中没有该患者
        if (patientId == null) {
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
                patientMapper.add(patient);
            } else {
                throw new IllegalArgumentException("身份证号不能为空");
            }
        } else {
            // 如果数据库中有该患者 则直接获取患者id
            patient.setPatientId(patientId);
        }
        // 添加用户患者关系
        patientMapper.addUserPatient(StpUtil.getLoginIdAsInt(), patient.getPatientId());

//        /*
//         * 放弃RSA解密
//         */
//        if (patientId == null) {
//            patient.setCleartextId(patient.getPlaintextId().substring(0, 12) + "******");
//            patientMapper.add(patient);
//        } else {
//            // 如果数据库中有该患者 则直接获取患者id
//            patient.setPatientId(patientId);
//        }
//        // 添加用户患者关系
//        patientMapper.addUserPatient(StpUtil.getLoginIdAsInt(), patient.getPatientId());
        return patient;
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
    public List<Patient> getByUserId(Integer id) {
        return patientMapper.getByUserId(id);
    }

    @Override
    public List<Patient> getAll() {
        return patientMapper.getAll();
    }

    @Override
    public Boolean existsById(Integer id) {
        return patientMapper.existsById(id);
    }
}
