package com.flash.records.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author: yangyang
 * @CreateTime: 2024-12-04
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationRecords {
    @TableId(type = IdType.AUTO)
    private Integer applicationId;
    private Integer userId;
    private Integer adminId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Shanghai")
    private Date applicationTime;
    private Integer status;
    private String authorId;
    private String name;
    private String orName;
    private String email;
    private String area;
}
