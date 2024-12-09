package com.sheng.hospital_server.service;

import com.sheng.hospital_server.pojo.User;

public interface UserService {

    void add(User user);

    void delete(Integer id);

    void update(User user);

    User getById(Integer id);

    Integer login(User user);

    Boolean existsById(Integer id);
}
