package com.example.demo.Mapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface VersionMapper {

    //获取最新版本号
    @Select("select max(id) from version")
    Long getLastVersion();

}