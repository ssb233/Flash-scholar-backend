package com.flash.user.repository;

import com.flash.user.dao.Institution;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstitutionRepository extends ElasticsearchRepository<Institution, String> {
    // 按 works_count 降序排序，并通过 Pageable 限制返回数量
//    @Query("""
//    {
//        "sort": [
//        {
//          "works_count": {
//            "order": "desc"
//          }
//        }
//      ]
//    }
//    """)
    Page<Institution> findAllByOrderByWorksCountDesc(Pageable pageable);

    Page<Institution> findAllByOrderByCitedByCountDesc(Pageable pageable);

    // Page<Institution> findAllByOrderByCountsByYearDesc(Pageable pageable);
    @Query("""
            {
              "query": {
                "match_all": {}
              },
              "sort": [
                {
                  "count_2024": { "order": "desc" }
                }
              ]
            }
            """)
    List<Institution> findInstitutionsSortedByYear2024Count(Pageable pageable);
}
