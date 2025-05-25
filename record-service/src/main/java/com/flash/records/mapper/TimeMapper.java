package com.flash.records.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.flash.records.dao.ApplicationRecords;
import com.flash.records.dao.Time;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface TimeMapper extends BaseMapper<Time> {
    @Select("select * from time WHERE group_id = #{groupId}")
    Time selectByGroup(@Param("groupId") Integer groupId);

    @Select("select * from time ")
    List<Time> selectAll();

    @Update("UPDATE time SET count = #{count} , value_time = #{valueTime}  WHERE group_id = #{groupId}")
    int updateStatusByGroup(@Param("count") Integer count, @Param("valueTime") Integer valueTime,@Param("groupId") Integer groupId);
}
