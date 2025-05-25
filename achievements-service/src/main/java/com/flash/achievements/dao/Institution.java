package com.flash.achievements.dao;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author : Extrafy
 * description  :
 * createDate   : 2024/12/8 20:08
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Institution {
    @TableId
    private String id;
    private String name;
    private Integer works_count;
    private Integer cited_by_count;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Shanghai")
    private Date updatedDate;

}

