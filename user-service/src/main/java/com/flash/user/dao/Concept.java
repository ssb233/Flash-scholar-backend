package com.flash.user.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.ognl.ObjectElementsAccessor;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @author yury
 * @description: TODO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "concepts")
public class Concept {
    private String id;
    private String name;
    private String description;
    private Long level;
    @Field(name = "related_concepts", type = FieldType.Object)
    private Object relatedConcepts;
    @Field(name = "h_index", type = FieldType.Long)
    private Long hIndex;
    @Field(name = "cited_by_count", type = FieldType.Long)
    private Long citedByCount;
    @Field(name = "works_count", type = FieldType.Long)
    private Long worksCount;
    @Field(name = "counts_by_year", type = FieldType.Object)
    private Object countsByYear;

    private Object ancestors;
    @Field(name = "image_url", type = FieldType.Text)
    private String imageUrl;


}
