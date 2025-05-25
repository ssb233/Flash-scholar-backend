package com.flash.achievements.dao;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author : Extrafy
 * description  :
 * createDate   : 2024/12/2 11:24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Work {
    private String id;
    private String title;
    private String abstractPart;
    private Integer citationCount;
    private String doi;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    private Date publishTime;
    private Integer collectionNum;
    private String type; //(1.论文, 2.获奖, 3.专利, 4.项目)
}


