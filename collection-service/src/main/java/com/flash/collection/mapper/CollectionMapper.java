package com.flash.collection.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.flash.collection.dao.Collection;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface CollectionMapper extends BaseMapper<Collection> {

    @Select("select * from collection WHERE uid = #{uid} AND achievement_id = #{achievementId} AND fid = #{fid}")
    Collection findByKey(@Param("uid") Integer uid, @Param("achievementId") String achievementId, @Param("fid") Integer fid);

    @Delete("DELETE FROM collection WHERE uid = #{uid} AND achievement_id = #{achievementId}")
    int deleteByKey(@Param("uid") Integer uid, @Param("achievementId") String achievementId);
}
