package com.flash.achievements.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : Extrafy
 * description  :
 * createDate   : 2024/12/11 20:39
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkReferencedShip {
    private String workId;
    private String referencedWorkId;
}

