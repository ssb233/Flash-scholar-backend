package com.flash.user.repository;

import com.flash.user.dao.Coauthor;
import com.flash.user.dao.Person;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

/**
 * @author yury
 * @description: TODO
 */
public interface CoauthorRepository extends Neo4jRepository<Coauthor, Long> {
    Coauthor findByPerson(Person person);
}
