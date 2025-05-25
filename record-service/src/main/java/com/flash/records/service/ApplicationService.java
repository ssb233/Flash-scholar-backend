package com.flash.records.service;

import com.flash.records.utils.BusinessException;
import com.flash.records.dao.*;

import java.util.List;

public interface ApplicationService {
    void addApply(Integer userId, String authorId, String name, String orName, String email, String area) throws BusinessException;

    void setApply(Integer uid, Integer status) throws BusinessException;

    List<ApplicationRecords> getApply() throws BusinessException;
}
