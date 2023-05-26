package com.mine.elasticsearch.repository;

import com.mine.elasticsearch.entity.ESPost;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.elasticsearch.repository.support.ElasticsearchEntityInformation;
import org.springframework.data.elasticsearch.repository.support.SimpleElasticsearchRepository;

import java.util.ArrayList;
import java.util.List;

public class ESPostRepository extends SimpleElasticsearchRepository<ESPost, String> implements IESPostRepository {

    public ESPostRepository(ElasticsearchEntityInformation<ESPost, String> metadata, ElasticsearchOperations operations) {
        super(metadata, operations);
    }

    @Override
    public List<ESPost> findByTitleAndContent(String title, String content, Query query) {
        List<ESPost> posts = new ArrayList<>();

        SearchHits<ESPost> searchHits = operations.search(query, ESPost.class);
        searchHits.forEach(hit -> {
            posts.add(hit.getContent());
        });
        return posts;
    }
}
