package com.flash.achievements.dao;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author yury
 * @description: TODO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Author {
    @TableId
    private String id;
    private Long uid;
    private String orcid;
    private String name;
    private Integer worksCount;
    private Integer citedByCount;
    private String lastKnownInstitution;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Shanghai")
    private Date updatedDate;
}
