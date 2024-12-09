package com.sheng.hospital_server.mapper;

import com.sheng.hospital_server.pojo.Admin;
import com.sheng.hospital_server.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AdminMapper {
    @Select("select * from admin where admin_id = #{adminId} and password = #{password}")
    Admin login(Admin admin);
}
