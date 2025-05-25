package com.flash.achievements.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author : Extrafy
 * description  :
 * createDate   : 2024/12/2 16:51
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatentDTO {
    private String name;
    private String type;
    private String applicationNumber;
    private String applicant;
    private String inventor;
    private String address;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Shanghai")
    private Date applyTime;
    private Integer userId;
}

