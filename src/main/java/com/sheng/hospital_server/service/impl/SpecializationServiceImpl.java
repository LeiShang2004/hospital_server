package com.sheng.hospital_server.service.impl;

import com.sheng.hospital_server.mapper.SpecializationMapper;
import com.sheng.hospital_server.pojo.Specialization;
import com.sheng.hospital_server.service.SpecializationService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecializationServiceImpl implements SpecializationService {
    @Resource
    private SpecializationMapper specializationMapper;

    @Override
    public void add(Specialization specialization) {
        specializationMapper.add(specialization);
    }

    @Override
    public void delete(Integer id) {
        specializationMapper.delete(id);
    }

    @Override
    public void update(Specialization specialization) {
        specializationMapper.update(specialization);
    }

    @Override
    public Specialization getById(Integer id) {
        return specializationMapper.getById(id);
    }

    @Override
    public List<Specialization> getAll() {
        return specializationMapper.getAll();
    }

    @Override
    public List<Specialization> getByDepartmentId(Integer departmentId) {
        return specializationMapper.getByDepartmentId(departmentId);
    }
}
