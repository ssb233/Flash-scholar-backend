package com.flash.achievements.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author       : Extrafy
 * description  : 
 * createDate   : 2024/12/11 21:01
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SourceDTO {
    private Boolean isOa;
    private String url;
    private String id;
    private String name;
}

