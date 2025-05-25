package com.flash.records.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.naming.ldap.PagedResultsControl;

/**
 * @Author: yangyang
 * @CreateTime: 2024-12-04
 * @Description:
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationDTO {
    private Integer userId;
    private String authorId;
    private String name;
    private String orName;
    private String email;
    private String area;
}
