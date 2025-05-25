package com.flash.user.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.neo4j.core.schema.Id;

import java.util.Date;

/**
 * @author yury
 * @description: TODO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "authors")
public class Author {
    @Id
    private String id;

    private String name;
    @Field(name = "organization_id", type = FieldType.Text)
    private String organizationId;
    @Field(name = "organization_name", type = FieldType.Text)
    private String organizationName;
    private Integer cited;
    @Field(name = "counts_by_year", type = FieldType.Object)
    private Object countsByYear;
    private Object fields;
}
