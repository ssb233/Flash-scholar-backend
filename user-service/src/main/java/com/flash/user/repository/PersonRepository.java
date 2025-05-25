package com.flash.user.repository;

import com.flash.user.dao.Person;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends Neo4jRepository<Person, Long> {
    Person findPersonEntityByName(String name);
    Person findPersonByAuthorId(String authorId);

    // @Query("match(n:Person) where n.authorId = \"{authorId}\" return n")
    Person getPersonByAuthorId(@Param("authorId")String authorId);
}
