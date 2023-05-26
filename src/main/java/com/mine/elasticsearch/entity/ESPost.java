package com.mine.elasticsearch.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Getter
@Setter
@NoArgsConstructor
@Document(indexName = "post")
public class ESPost {
    @Id
    private String id;

    private String title;
    private String content;

    private String isPublish;

    private String userId;

    private String createDate;
}
