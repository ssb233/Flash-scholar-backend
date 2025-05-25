package com.flash.records.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: yangyang
 * @CreateTime: 2024-12-04
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClaimDTO {
    private Integer uid;
    private List<String> documentIds;
}
