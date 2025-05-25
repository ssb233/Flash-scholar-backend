package com.flash.user.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.ognl.ObjectElementsAccessor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

/**
 * @author yury
 * @description: TODO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "institutions")
public class Institution {
    private String id;
    private String name;
    @Field(name = "cited_by_count", type = FieldType.Long)
    private Long citedByCount;
    @Field(name = "counts_by_year", type = FieldType.Object)
    private Object countsByYear;

    @Field(name = "works_count", type = FieldType.Long)
    private Long worksCount;
    private Object concepts;
    @Field(name = "homepage_url", type = FieldType.Text)
    private String homepageUrl;
    @Field(name = "img_url", type = FieldType.Text)
    private String imgUrl;
}
