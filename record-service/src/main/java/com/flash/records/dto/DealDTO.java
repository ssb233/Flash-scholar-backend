package com.flash.records.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: yangyang
 * @CreateTime: 2024-12-04
 * @Description:
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DealDTO {
    private String content;
    private Integer cost;
}
