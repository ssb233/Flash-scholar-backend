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
public class ClaimRecord {
    @TableId(type = IdType.AUTO)
    private Integer cid;
    private Integer uid;
    private Integer adminId;
    private Integer status;
    private String reason;
    private String workId;
    private String response;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Shanghai")
    private Date time;
}
