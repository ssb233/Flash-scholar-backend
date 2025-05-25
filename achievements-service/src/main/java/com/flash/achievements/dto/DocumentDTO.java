package com.flash.achievements.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author : Extrafy
 * description  :
 * createDate   : 2024/12/2 11:25
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocumentDTO {
    private String documentId;
    private String name;
    private String abstractPart;
    private List<SourceDTO> sourceDTOS;
    private List<ConceptDTO> conceptDTOS;
    private Integer citationCount;
    private String doi;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    private Date publishTime;
    private Integer collectionNum;
    private String type; //(1.论文, 2.获奖, 3.专利, 4.项目)
    private List<AuthorInfo> authors; // 添加作者信息
    private List<SchoolInfo> schools; // 添加机构信息
    private Integer rank; // 搜索展示顺序
}

