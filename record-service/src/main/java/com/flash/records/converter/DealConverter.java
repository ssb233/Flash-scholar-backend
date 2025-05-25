package com.flash.records.converter;

import com.flash.records.dao.DealRecord;
import com.flash.records.dto.DealDTO;

/**
 * @Author: yangyang
 * @CreateTime: 2024-12-04
 * @Description:
 */

public class DealConverter {
    public static DealDTO convertDeal(DealRecord deal){
        if (deal == null) {
            return null;
        }
        DealDTO dealDTO = new DealDTO();
        dealDTO.setContent(deal.getContent());
        dealDTO.setCost(deal.getCost());
        return dealDTO;
    }
}
