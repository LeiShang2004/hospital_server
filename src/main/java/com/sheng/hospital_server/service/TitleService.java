package com.sheng.hospital_server.service;

import com.sheng.hospital_server.pojo.Title;

import java.util.List;

public interface TitleService {
    void add(Title title);

    void delete(Integer id);

    void update(Title title);

    Title getById(Integer id);

    List<Title> getAll();
}
