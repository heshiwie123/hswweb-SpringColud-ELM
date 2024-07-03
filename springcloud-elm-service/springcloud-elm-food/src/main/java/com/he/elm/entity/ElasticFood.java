package com.he.elm.entity;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@Document(indexName = "foods") //制定为elasticsearch的资源
public class ElasticFood {
    @Id
    @Field(type = FieldType.Keyword)
    private String id;
    @Field(type = FieldType.Text)
    private String foodName;
    @Field(type = FieldType.Text)
    private String foodExplain;
    private String foodImg;
    @Field(type = FieldType.Double)
    private Double foodPrice;
    @Field(type = FieldType.Long)
    private Integer businessId;
    @Field(type = FieldType.Text)
    private String remarks;
}
