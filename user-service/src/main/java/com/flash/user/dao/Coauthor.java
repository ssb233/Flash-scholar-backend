package com.flash.user.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.RelationshipId;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

import java.util.List;

/**
 * @author yury
 * @description: TODO
 */
@RelationshipProperties
@AllArgsConstructor
@NoArgsConstructor
public class Coauthor {
    @RelationshipId
    private Long id;

    public Integer count;

    @TargetNode // 相当于@StartNode
    public Person person;

    // 参数1是目标关系实体节点 参数2是关系属性
    //    Roles 参数1：Person实体，演员的出生年和姓名；参数2：演员名字列表（考虑到一个演员可能参演多个角色）
    public Coauthor(Person person, Integer count) {
        this.person = person;
        this.count = count;
    }
}
