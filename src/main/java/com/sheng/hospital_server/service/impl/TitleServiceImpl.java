package com.sheng.hospital_server.service.impl;

import com.sheng.hospital_server.mapper.TitleMapper;
import com.sheng.hospital_server.pojo.Title;
import com.sheng.hospital_server.service.TitleService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TitleServiceImpl implements TitleService {

    @Resource
    private TitleMapper titleMapper;

    @Override
    public void add(Title title) {
        titleMapper.add(title);
    }

    @Override
    public void delete(Integer id) {
        titleMapper.delete(id);
    }

    @Override
    public void update(Title title) {
        titleMapper.update(title);
    }

    @Override
    public Title getById(Integer id) {
        return titleMapper.getById(id);
    }

    @Override
    public List<Title> getAll() {
        return titleMapper.getAll();
    }
}
