package com.flash.records.converter;

import com.flash.records.dto.ClaimDTO;
import com.flash.records.dao.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: yangyang
 * @CreateTime: 2024-12-09
 * @Description:
 */

public class ClaimConverter {
    public static List<ClaimDTO> convertClaim(List<ClaimRecord> claimRecords, List<String> documents){
        List<ClaimDTO> claimDTOS = new ArrayList<>();
        for (ClaimRecord claim:claimRecords) {
            if (claim == null) {
                return null;
            }
            ClaimDTO claimDTO = new ClaimDTO();
            claimDTO.setUid(claim.getUid());
            claimDTO.setDocumentIds(documents);
            claimDTOS.add(claimDTO);
        }
        return claimDTOS;
    }
}
