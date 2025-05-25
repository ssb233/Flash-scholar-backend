package com.flash.achievements.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author : Extrafy
 * description  :
 * createDate   : 2024/12/1 11:25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AwardDTO {
    private Integer id;
    private Integer authorId;
    private String authorName;
    private String title;
    private String keyWords;
    private Date date;
}

