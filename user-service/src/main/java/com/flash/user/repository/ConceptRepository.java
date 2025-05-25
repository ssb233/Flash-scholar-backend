package com.flash.user.repository;

import com.flash.user.dao.Concept;
import com.flash.user.dao.Institution;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConceptRepository extends ElasticsearchRepository<Concept, String> {
    Page<Concept> findAllByOrderByWorksCountDesc(Pageable pageable);

    Page<Concept> findAllByOrderByCitedByCountDesc(Pageable pageable);
}
