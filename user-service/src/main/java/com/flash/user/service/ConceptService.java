package com.flash.user.service;

import com.flash.user.dao.Concept;
import com.flash.user.dao.Institution;
import com.flash.user.utils.BusinessException;
import org.springframework.data.domain.Page;

import java.util.Optional;

/**
 * @author yury
 * @description: TODO
 */
public interface ConceptService {

    Optional<Concept> findById(String id) throws BusinessException;

    Long getConceptCount() throws BusinessException;

    // 按 works_count 降序排序，并通过 Pageable 限制返回数量
    Page<Concept> findAllByOrderByWorksCountDesc();

    Page<Concept> findAllByOrderByCitedByCountDesc();
}
