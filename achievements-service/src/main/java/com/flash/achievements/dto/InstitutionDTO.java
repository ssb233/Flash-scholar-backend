package com.flash.achievements.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : Extrafy
 * description  :
 * createDate   : 2024/12/11 15:09
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
// 数据注入
public class InstitutionDTO {
    private String institutionId;
    private String institutionName;
}

