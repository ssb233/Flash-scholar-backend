package com.flash.records.service;
import com.flash.records.dto.ClaimDTO;
import com.flash.records.utils.BusinessException;

import java.util.List;
import com.flash.records.dao.ClaimRecord;

/**
 * @Author: yangyang
 * @CreateTime: 2024-12-09
 * @Description:
 */

public interface ClaimService {
    List<ClaimDTO> addedClaim(Integer uid, List<String> documents) throws BusinessException;
    void setClaim(Integer uid,Integer documentId,Integer ans) throws BusinessException;
    List<ClaimRecord> getAllClaim() throws BusinessException;
    List<ClaimRecord> getClaimByStatus(Integer status) throws BusinessException;
}
