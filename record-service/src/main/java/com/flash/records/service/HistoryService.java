package com.flash.records.service;

import com.flash.records.dao.History;
import com.flash.records.utils.BusinessException;

import java.util.Date;
import java.util.List;

public interface HistoryService {
    void addHistory(Integer uid, String workId, String title, Integer count, String name, String publisher, Date publishTime) throws BusinessException;
    List<History> getHistory(Integer uid) throws BusinessException;
}
