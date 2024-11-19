package com.sheng.hospital_server.controller;

import com.sheng.hospital_server.mapper.DoctorMapper;
import com.sheng.hospital_server.pojo.Doctor;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * 医生控制器
 */
@Slf4j
@RestController
@RequestMapping("/doctors")
@CrossOrigin//跨域
public class DoctorController {
//    @Resource
//    DoctorMapper doctorMapper;
//
//    @GetMapping("/{id}")
//    public Doctor getById(@PathVariable Integer id) {
//        Doctor doctor = doctorMapper.getById(id);
//        return doctor;
//    }
}
