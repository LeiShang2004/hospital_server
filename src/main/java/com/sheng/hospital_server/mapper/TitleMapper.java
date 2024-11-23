package com.sheng.hospital_server.mapper;

import com.sheng.hospital_server.pojo.Title;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TitleMapper {
    @Select("select * from title")
    List<Title> getAll();

    @Select("select * from title where title_id = #{id}")
    Title getById(Integer id);

    @Insert("insert into title(name,fee) values(#{title.name},#{title.fee})")
    void add(Title title);

    @Delete("delete from title where title_id = #{id}")
    void delete(Integer id);

    @Update("update title set name=#{title.name},fee=#{title.fee} where title_id = #{title.id}")
    void update(Title title);
}
