package com.flash.user.dao;

import co.elastic.clients.util.DateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.neo4j.core.schema.Id;

import java.sql.Date;

/**
 * @author yury
 * @description: TODO
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "works")
public class Work {
    @Id
    @Field
    private String workId;
    private Object authorDTOS;
    private Object institutionDTOS;
    private String title;
    private String abstracts;
    private Object conceptDTOS;
    private Long cited;
    private String type;
    private String doi;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    private Date publishTime;
    private Object sources;
    private Object relatedDTOS;
    private Object referencesDTOS;

}
