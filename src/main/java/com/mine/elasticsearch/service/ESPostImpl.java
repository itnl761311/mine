package com.mine.elasticsearch.service;

import com.mine.elasticsearch.entity.ESPost;
import com.mine.elasticsearch.repository.IESPostRepository;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.matchAllQuery;

@Service
public class ESPostImpl implements IESPost {

    IESPostRepository postRepository;
    public ESPostImpl(IESPostRepository postRepository){
        this.postRepository = postRepository;
    }
    @Override
    public ESPost getPostById(String id) {
//        List<ESPost> posts = new ArrayList<>();
//        Criteria criteria = new Criteria("title").is("").and("content").is(new Criteria().contains(""));
//        Query query = new CriteriaQuery(criteria);
//        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery().must(BoolQueryBuilder.parseInnerQueryBuilder(query));
//        NativeSearchQuery build = new NativeSearchQueryBuilder().withQuery(matchAllQuery()).build();

//        operations.search(qr, ESPost.class).getSearchHits().forEach(searchHit -> {
//            posts.add(searchHit.getContent());
//        });
        return postRepository.findById(id).orElse(null);
    }

    @Override
    public List<ESPost> findESPostByTitleAndContent(String title, String content) {
        Criteria criteria = new Criteria("title").is(title);
        Criteria criteria1 = new Criteria().and(criteria);
        Query query = new CriteriaQuery(criteria);
        return postRepository.findByTitleAndContent(title,content,query);
    }
}
