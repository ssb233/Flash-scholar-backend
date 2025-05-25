package com.flash.records.dao;

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
public class DealRecord {
    private Integer deal_id;
    private String content;
    private Integer cost;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Shanghai")
    private Date time;
}
