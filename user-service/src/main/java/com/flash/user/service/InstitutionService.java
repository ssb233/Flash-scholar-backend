package com.flash.user.service;

import com.flash.user.utils.BusinessException;
import com.flash.user.dao.Institution;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface InstitutionService {

    Optional<Institution> findById(String id) throws BusinessException;

    Long getInstitutionCount() throws BusinessException;

    // 按 works_count 降序排序，并通过 Pageable 限制返回数量
    Page<Institution> findAllByOrderByWorksCountDesc();

    Page<Institution> findAllByOrderByCitedByCountDesc();
}
