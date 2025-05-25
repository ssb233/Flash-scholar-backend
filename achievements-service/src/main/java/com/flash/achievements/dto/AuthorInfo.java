package com.flash.achievements.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : Extrafy
 * description  :
 * createDate   : 2024/12/8 20:08
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorInfo {
    private String authorId;
    private String authorName;
    private String institutionName;
}

