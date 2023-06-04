package com.mine.elasticsearch.repository.defination;

import com.mine.elasticsearch.entity.ESPost;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.Query;

import java.util.List;

public interface IESPostDefinitionRepository {
    List<ESPost> searchAll(Query query, ElasticsearchOperations operations);
}
