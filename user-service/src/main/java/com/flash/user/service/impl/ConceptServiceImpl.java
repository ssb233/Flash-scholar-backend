package com.flash.user.service.impl;

import com.flash.user.dao.Concept;
import com.flash.user.dao.Institution;
import com.flash.user.repository.ConceptRepository;
import com.flash.user.repository.InstitutionRepository;
import com.flash.user.service.ConceptService;
import com.flash.user.utils.BusinessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * @author yury
 * @description: TODO
 */
@Service
public class ConceptServiceImpl implements ConceptService {
    @Resource
    private ConceptRepository conceptRepository;

    @Override
    public Optional<Concept> findById(String id) throws BusinessException {
        Optional<Concept> institution = conceptRepository.findById(id);
        if (institution.isEmpty()) {
            throw new BusinessException(400, "机构不存在");
        }
        return institution;
    }



    @Override
    public Long getConceptCount() throws BusinessException {
        return conceptRepository.count();
    }

    @Override
    public Page<Concept> findAllByOrderByWorksCountDesc() {
        Pageable pageable = PageRequest.of(0, 30, Sort.by(Sort.Order.desc("works_count")));
        return conceptRepository.findAllByOrderByWorksCountDesc(pageable);
    }

    public Page<Concept> findAllByOrderByCitedByCountDesc() {
        Pageable pageable = PageRequest.of(0, 30, Sort.by(Sort.Order.desc("cited_by_count")));
        return conceptRepository.findAllByOrderByCitedByCountDesc(pageable);
    }
}
