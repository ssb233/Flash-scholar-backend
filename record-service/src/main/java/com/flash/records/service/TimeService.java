package com.flash.records.service;

import com.flash.records.dao.Time;
import com.flash.records.utils.BusinessException;

import java.util.List;

public interface TimeService {
    void UpdateTime(Integer value,Integer group) throws BusinessException;
    List<Double> getTime() throws BusinessException;
}
