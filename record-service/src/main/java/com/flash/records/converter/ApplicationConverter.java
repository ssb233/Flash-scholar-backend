package com.flash.records.converter;

import com.flash.records.dto.ApplicationDTO;
import com.flash.records.dao.*;

/**
 * @Author: yangyang
 * @CreateTime: 2024-12-09
 * @Description:
 */

public class ApplicationConverter {
    public static ApplicationDTO convertApplication(ApplicationRecords application){
        if (application == null){
            return null;
        }
        ApplicationDTO applicationDTO = new ApplicationDTO();
        applicationDTO.setUserId(application.getUserId());
        return applicationDTO;
    }
}
