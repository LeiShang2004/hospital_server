package com.sheng.hospital_server.service.impl;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.DeleteRequest;
import co.elastic.clients.elasticsearch.core.IndexRequest;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.sheng.hospital_server.mapper.DoctorMapper;
import com.sheng.hospital_server.pojo.Doctor;
import com.sheng.hospital_server.pojo.DoctorES;
import com.sheng.hospital_server.pojo.ScheduleInfo;
import com.sheng.hospital_server.service.DoctorService;
import com.sheng.hospital_server.service.RedisService;
import com.sheng.hospital_server.service.ScheduleService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class DoctorServiceImpl implements DoctorService {
    @Resource
    private DoctorMapper doctorMapper;

    @Resource
    private RedisService redisService;

    @Resource
    private ScheduleService scheduleService;

    @Resource
    private ElasticsearchClient elasticClient;

    @Override
    public Doctor getById(Integer id) {
        Doctor doctor = doctorMapper.getById(id);
        // 补全医生信息
        this.complete(doctor);
        return doctor;
    }

    @Override
    public List<Doctor> getBySpecializationId(Integer specializationId) {
        List<Doctor> doctors = doctorMapper.getBySpecializationId(specializationId);
        for (Doctor doctor : doctors) {
            // 补全医生信息
            this.complete(doctor);
        }
        return doctors;
    }

    @Override
    public List<Doctor> getByDepartmentId(Integer departmentId) {
        List<Doctor> doctors = doctorMapper.getByDepartmentId(departmentId);
        for (Doctor doctor : doctors) {
            // 补全医生信息
            this.complete(doctor);
        }
        return doctors;
    }

    @Override
    public List<Doctor> getAll() {
        List<Doctor> doctors = doctorMapper.getAll();
        for (Doctor doctor : doctors) {
            // 补全医生信息
            this.complete(doctor);
        }
        return doctors;
    }

    @Override
    public List<ScheduleInfo> getByName(String name) {
        List<Doctor> byName = doctorMapper.getByName(name);
        List<ScheduleInfo> scheduleInfos = new ArrayList<>();
        // 当前sql日期
        java.util.Date date = new java.util.Date();
        java.sql.Date startDate = new java.sql.Date(date.getTime() + 24 * 60 * 60 * 1000);
        // 未来第七天的日期
        java.sql.Date endDate = new java.sql.Date(date.getTime() + 7 * 24 * 60 * 60 * 1000);
        for (Doctor doctor : byName) {
            List<ScheduleInfo> infoByDoctorIdAndDate = scheduleService.getInfoByDoctorIdAndDate(doctor.getDoctorId(), startDate, endDate);
            scheduleInfos.addAll(infoByDoctorIdAndDate);
        }
        return scheduleInfos;
    }

    /**
     * 使用关键词查询医生介绍并返回匹配项的id。
     *
     * @param word 关键词
     * @return 包含匹配项id的列表
     */
    @Override
    public List<ScheduleInfo> getByIntroduction(String word) throws IOException {

        SearchResponse<DoctorES> response = elasticClient.search(s -> s.index("doctor").query(q -> q.match(m -> m.field("introduction").query(word))), DoctorES.class);

        List<Hit<DoctorES>> hits = response.hits().hits();
        List<Integer> ids = hits.stream().map(Hit::source).filter(Objects::nonNull).map(DoctorES::getId).toList();
        log.info("匹配到的医生id: {}", ids);

        List<ScheduleInfo> scheduleInfos = new ArrayList<>();
        // 当前sql日期
        java.util.Date date = new java.util.Date();
        java.sql.Date startDate = new java.sql.Date(date.getTime() + 24 * 60 * 60 * 1000);
        // 未来第七天的日期
        java.sql.Date endDate = new java.sql.Date(date.getTime() + 7 * 24 * 60 * 60 * 1000);
        for (Integer id : ids) {
            List<ScheduleInfo> infoByDoctorIdAndDate = scheduleService.getInfoByDoctorIdAndDate(id, startDate, endDate);
            scheduleInfos.addAll(infoByDoctorIdAndDate);
        }
        return scheduleInfos;
    }

    /**
     * 补全医生信息
     * 通过RedisService获取科室名等，补全医生信息
     *
     * @param doctor 不完全的医生信息
     */
    @Override
    public void complete(Doctor doctor) {
        doctor.setDepartmentName(redisService.getDepartmentName(doctor.getDepartmentId()));
        doctor.setSpecializationName(redisService.getSpecializationName(doctor.getSpecializationId()));
        doctor.setTitleName(redisService.getTitleName(doctor.getTitleId()));
        doctor.setTitleFee(redisService.getTitleFee(doctor.getTitleId()));
    }

    @Override
    public Boolean existsById(Integer id) {
        return doctorMapper.existsById(id);
    }

    @Override
    public void add(Doctor doctor) throws IOException {
        doctorMapper.add(doctor);
        toES(doctor);
    }

    @Override
    public void delete(Integer id) throws IOException {
        doctorMapper.deleteById(id);
        DeleteRequest deleteRequest = DeleteRequest.of(s -> s
                .index("doctor")
                .id(id.toString()));
        elasticClient.delete(deleteRequest);
    }

    @Override
    public void update(Doctor doctor) throws IOException {
        doctorMapper.update(doctor);
        toES(doctor);
    }

    private void toES(Doctor doctor) throws IOException {
        DoctorES doctorES = new DoctorES();
        doctorES.setId(doctor.getDoctorId());
        doctorES.setIntroduction(doctor.getIntroduction());
        IndexRequest<DoctorES> indexRequest = IndexRequest.of(b -> b
                .index("doctor")
                .id(doctor.getDoctorId().toString())
                .document(doctorES));
        IndexResponse index = elasticClient.index(indexRequest);
        System.out.println(index);
    }
}
