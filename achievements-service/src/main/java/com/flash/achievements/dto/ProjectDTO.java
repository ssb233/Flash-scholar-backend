package com.flash.achievements.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : Extrafy
 * description  :
 * createDate   : 2024/12/1 23:39
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDTO {
    private Integer userId;
    private String host;
    private Double money;
    private Integer organizationId;
    private String projectName;
}

