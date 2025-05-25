package com.flash.collection.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Collection {
    @TableId(type = IdType.AUTO)
    private Integer cid;
    private Integer uid;
    private String achievementId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Shanghai")
    private Date time;
    private Integer fid;
    // 收藏成果的具体信息
    private Integer quoteTime;
    private String authors;
    private String journal;
    private String title;
    @JsonFormat(pattern = "yyyy", timezone = "Asia/Shanghai")
    private Date date;
}