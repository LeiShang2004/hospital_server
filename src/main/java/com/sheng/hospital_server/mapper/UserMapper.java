package com.sheng.hospital_server.mapper;

import com.sheng.hospital_server.pojo.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    @Select("select * from user where user_id = #{userId}")
    User getById(Integer userId);

    @Select("select * from user where user_id = #{userId} and password = #{password}")
    User login(User user);

    @Select("select * from user ")
    User getAll();

    @Insert("insert into user(name, password, phone, balance) values(#{name}, #{password}, #{phone}, #{balance})")
    void add(User user);

    @Update("update user set name = #{name}, password = #{password}, phone = #{phone}, balance = #{balance} where user_id = #{userId}")
    void update(User user);

    @Delete("delete from user where user_id = #{userId}")
    void delete(Integer userId);

    @Select("select exists(select 1 from user where user_id = #{userId})")
    Boolean existsById(Integer userId);
}
