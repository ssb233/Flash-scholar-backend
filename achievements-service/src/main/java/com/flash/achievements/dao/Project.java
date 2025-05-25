package com.flash.achievements.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : Extrafy
 * description  :
 * createDate   : 2024/12/1 23:23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Project {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String projectName;
    private String host;
    private Integer organizationId;
    private Double money;
    private Integer userId;
}

