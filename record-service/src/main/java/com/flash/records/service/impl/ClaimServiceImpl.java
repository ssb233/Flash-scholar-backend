package com.flash.records.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.flash.records.dto.ClaimDTO;
import com.flash.records.mapper.ClaimMapper;
import com.flash.records.service.ClaimService;
import com.flash.records.utils.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.flash.records.dao.*;
import com.flash.records.converter.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: yangyang
 * @CreateTime: 2024-12-09
 * @Description:
 */
@Slf4j
@Service
public class ClaimServiceImpl implements ClaimService {

    @Autowired
    private ClaimMapper claimMapper;

    public List<ClaimRecord> getAllClaim() {
        return claimMapper.selectList(null);
    }

    @Override
    public List<ClaimRecord> getClaimByStatus(Integer status) {
        QueryWrapper<ClaimRecord> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status", status);
        return claimMapper.selectList(queryWrapper);
    }

    @Override
    public List<ClaimDTO> addedClaim(Integer uid, List<String> documents) throws BusinessException{
        if (uid == null){
            throw new BusinessException(400, "作者不存在");
        }
        if (documents == null){
            System.out.println("nonono");
        }
        List<ClaimRecord> claimRecords = new ArrayList<>();
        for (String documentID:documents) {

            Date now = new Date();
            ClaimRecord claim = new ClaimRecord(
                    null,
                    uid,
                    1,
                    0, "Some material",
                    documentID,
                    "",
                    now
                    );
            claimMapper.insert(claim);
            claimRecords.add(claim);
        }
        return ClaimConverter.convertClaim(claimRecords,documents);
    }

    @Override
    public void setClaim(Integer uid, Integer documentId, Integer ans) throws BusinessException {

    }

}
