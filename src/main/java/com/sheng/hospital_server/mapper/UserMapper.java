package com.sheng.hospital_server.mapper;

import com.sheng.hospital_server.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("select * from user where user_id = #{userId}")
    User getById(Integer userId);


    @Select("select * from user where phone = #{phone} and password = #{password}")
    User login(User user);

    @Select("select user_id,name,phone from user")
    List<User> getAll();

    @Select("select * from patient where name = #{name}")
    User getByName(String name);

    @Insert("insert into user(name, password, phone, balance) values(#{name}, #{password}, #{phone}, #{balance})")
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    void add(User user);

    @Update("update user set name = #{name}, password = #{password}, phone = #{phone}, balance = #{balance} where user_id = #{userId}")
    void update(User user);

    @Delete("delete from user where user_id = #{userId}")
    void delete(Integer userId);

    @Select("select exists(select 1 from user where user_id = #{userId})")
    Boolean existsById(Integer userId);
}
