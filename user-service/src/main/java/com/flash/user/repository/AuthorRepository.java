package com.flash.user.repository;

import com.flash.user.dao.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends ElasticsearchRepository<Author, String> {
    /**
     * 自定义通过name查询
     *
     * @param name
     * @return
     */
    List<Author> findByName(String name);

//    @Query("""
//    {
//        "function_score": {
//          "query": {
//            "match_all": {}
//          },
//          "random_score": {}
//        }
//    }
//    """)
    @Query("""
    {
        "function_score": {
          "query": {
            "match_all": {}
          },
          "random_score": {}
        }
    }
    """)
    Page<Author> findRandomly(Pageable pageable);
}
