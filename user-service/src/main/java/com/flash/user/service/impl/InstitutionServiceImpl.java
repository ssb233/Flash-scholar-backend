package com.flash.user.service.impl;

import com.flash.user.dao.Institution;
import com.flash.user.repository.InstitutionRepository;
import com.flash.user.service.InstitutionService;
import com.flash.user.utils.BusinessException;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@Service
public class InstitutionServiceImpl implements InstitutionService {

    @Resource
    private InstitutionRepository institutionRepository;

    @Override
    public Optional<Institution> findById(String id) throws BusinessException {
        Optional<Institution> institution = institutionRepository.findById(id);
        if (institution.isEmpty()) {
            throw new BusinessException(400, "机构不存在");
        }
        return institution;
    }



    @Override
    public Long getInstitutionCount() throws BusinessException {
        return institutionRepository.count();
    }

    @Override
    public Page<Institution> findAllByOrderByWorksCountDesc() {
        Pageable pageable = PageRequest.of(0, 30, Sort.by(Sort.Order.desc("works_count")));
        return institutionRepository.findAllByOrderByWorksCountDesc(pageable);
    }

    public Page<Institution> findAllByOrderByCitedByCountDesc() {
        Pageable pageable = PageRequest.of(0, 30, Sort.by(Sort.Order.desc("cited_by_count")));
        return institutionRepository.findAllByOrderByCitedByCountDesc(pageable);
    }

}
