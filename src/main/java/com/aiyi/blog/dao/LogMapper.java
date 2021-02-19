package com.aiyi.blog.dao;

import com.aiyi.blog.entity.Log;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LogMapper {

    @Insert("<script>INSERT INTO blog_log(create_time) VALUES <foreach collection =\"logs\" item=\"log\" separator =\",\">\n" +
            "         (#{log.createTime})\n" +
            "</foreach ></script>")
    void insertList(@Param("logs") List<Log> logs);

}
