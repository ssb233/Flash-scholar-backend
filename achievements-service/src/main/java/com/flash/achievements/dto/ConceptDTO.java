package com.flash.achievements.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : Extrafy
 * description  :
 * createDate   : 2024/12/11 15:12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConceptDTO {
    private String conceptId;
    private String conceptName;
    private Integer conceptLevel;
}

