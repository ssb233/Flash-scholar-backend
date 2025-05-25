package com.flash.achievements.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author : Extrafy
 * description  :
 * createDate   : 2024/12/1 11:38
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Award {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer authorId;
    private String title;
    private String keyWords;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Shanghai")
    private Date awardTime;
}

