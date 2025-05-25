package com.flash.achievements.converter;

import com.flash.achievements.dao.Patent;
import com.flash.achievements.dto.PatentDTO;

/**
 * @author : Extrafy
 * description  :
 * createDate   : 2024/12/2 16:50
 */
public class PatentConverter {
    public static PatentDTO convertPatent(Patent patent){
        PatentDTO patentDTO = new PatentDTO();
        patentDTO.setName(patent.getName());
        patentDTO.setApplicant(patent.getApplicant());
        patentDTO.setAddress(patent.getAddress());
        patentDTO.setType(patent.getType());
        patentDTO.setInventor(patent.getInventor());
        patentDTO.setUserId(patent.getUserId());
        patentDTO.setApplyTime(patent.getApplyTime());
        patentDTO.setApplicationNumber(patent.getApplicationNumber());
        return patentDTO;
    }

    public static Patent convertPatent(PatentDTO patentDTO){
        Patent patent = new Patent();
        patent.setType(patentDTO.getType());
        patent.setAddress(patentDTO.getAddress());
        patent.setInventor(patentDTO.getInventor());
        patent.setApplicationNumber(patentDTO.getApplicationNumber());
        patent.setApplyTime(patentDTO.getApplyTime());
        patent.setName(patentDTO.getName());
        patent.setApplicant(patentDTO.getApplicant());
        patent.setUserId(patentDTO.getUserId());
        return patent;
    }
}

