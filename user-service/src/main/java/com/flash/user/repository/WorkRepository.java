package com.flash.user.repository;

import com.flash.user.dao.Work;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author yury
 * @description: TODO
 */

@Repository
public interface WorkRepository extends ElasticsearchRepository<Work, String> {

    Optional<Work> findByWorkId(String workId);
    // Optional<Work> findByDoi(String doi);

    // Optional<Work> findById(String id);

    List<Work> findByTitle(String title);
}
