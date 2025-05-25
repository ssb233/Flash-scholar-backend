package com.flash.achievements.service;

import com.flash.achievements.dao.Patent;
import com.flash.achievements.dto.PatentDTO;
import com.flash.achievements.utils.BusinessException;

import java.util.Date;
import java.util.List;

public interface PatentService {
    PatentDTO addPatent(PatentDTO patentDTO) throws BusinessException;
    void  deletePatent(Integer id) throws BusinessException;
    List<PatentDTO> getAllPatents() throws BusinessException;
    PatentDTO updatePatent(PatentDTO patentDTO) throws  BusinessException;
}
