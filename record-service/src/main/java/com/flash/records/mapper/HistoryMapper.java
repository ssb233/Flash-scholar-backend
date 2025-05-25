package com.flash.records.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.flash.records.dao.History;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface HistoryMapper extends BaseMapper<History> {
    @Select("select * from history WHERE uid = #{uid} ")
    History selectByUid(@Param("uid") Integer uid);
}
