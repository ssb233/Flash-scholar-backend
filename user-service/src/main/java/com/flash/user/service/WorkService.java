package com.flash.user.service;

import com.flash.user.dao.Author;
import com.flash.user.dao.Work;
import com.flash.user.utils.BusinessException;

import java.util.List;
import java.util.Optional;

public interface WorkService {
    Long getNumOfWork() throws BusinessException;
    Optional<Work> findByWorkId(String id) throws BusinessException;
    List<Work> findByTitle(String title) throws BusinessException;
}
