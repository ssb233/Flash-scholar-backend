package com.flash.user.service.impl;

import co.elastic.clients.elasticsearch._types.query_dsl.QueryStringQuery;
import com.flash.user.dao.Work;
import com.flash.user.repository.WorkRepository;
import com.flash.user.service.WorkService;
import com.flash.user.utils.BusinessException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * @author yury
 * @description: TODO
 */

@Service
public class WorkServiceImpl implements WorkService {
    @Resource
    private WorkRepository workRepository;

    public Long getNumOfWork() {
        return workRepository.count();
    }

    // 根据 ID 查询
    public Optional<Work> findByWorkId(String id) throws BusinessException {
        System.out.println(id);
        Optional<Work> work = workRepository.findByWorkId(id);

        System.out.println(work);

        if (work.isEmpty()) {
            throw new BusinessException(400, "成果不存在");
        }
        return work;
    }

    // 根据 ID 查询
    public List<Work> findByTitle(String title) throws BusinessException {
        System.out.println(title);
        List<Work> workList = workRepository.findByTitle(title);

        if (workList.isEmpty()) {
            throw new BusinessException(400, "成果不存在");
        }
        return workList;
    }
}
