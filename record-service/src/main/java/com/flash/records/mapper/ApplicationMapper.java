package com.flash.records.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.flash.records.dao.ApplicationRecords;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ApplicationMapper extends BaseMapper<ApplicationRecords> {
    // 1. 根据 UID 查询申请记录
    @Select("select * from application_records WHERE user_id = #{userId} and status = 0 ")
    List<ApplicationRecords> selectByUid(@Param("userId") Integer userId);

    @Update("UPDATE application_records SET status = #{status} WHERE user_id = #{userId}")
    int updateStatusByUserId(@Param("userId") Integer userId, @Param("status") Integer status);
}
