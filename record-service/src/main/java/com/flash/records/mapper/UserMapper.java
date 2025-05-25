package com.flash.records.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.flash.records.dao.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("select * from user WHERE uid = #{uid}")
    User findByIndex(@Param("uid") Integer uid);
}