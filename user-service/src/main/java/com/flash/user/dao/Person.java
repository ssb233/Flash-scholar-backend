package com.flash.user.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.*;


@Node("Person")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    @Id
    @GeneratedValue
    private Long id;

    private String authorId;
    private String name;
    private int worksCount; //

    // private Map<String, Integer> coauthorMap = new HashMap<>();
//    public class CoauthorMap {
//        public Map<String, Integer> coauthorMap;
//
//        public CoauthorMap() {
//            this.coauthorMap = new HashMap<>();
//        }
//
//        public Map<String, Integer> getCoauthorMap() {
//            return coauthorMap;
//        }
//    }

    // private CoauthorMap coauthorMap = new CoauthorMap();

    public Person(String authorId, String name) {
        this.id = null;// 生成node时自动生成
        this.authorId = authorId;
        this.name = name;
        this.worksCount = 0;
    }

    // 定义一个关系（合著）
    @Relationship(type = "COAUTHOR")
    private Set<Coauthor> coauthors = new HashSet<>();
    // 注意这些关系最终的箭头指向是当前实体，即TargetNode（PersonEntity）->当前定义Relationship的实体（MovieEntity）
}

