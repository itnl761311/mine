package com.mine.elasticsearch.repository.defination;

import com.mine.elasticsearch.entity.ESPost;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ESPostDefinitionRepository implements IESPostDefinitionRepository{

    @Override
    public List<ESPost> searchAll(Query query, ElasticsearchOperations operations){
        List<ESPost> posts = new ArrayList<>();
        SearchHits<ESPost> searchHits = operations.search(query, ESPost.class);
        searchHits.forEach(hit -> {
            posts.add(hit.getContent());
        });
        return posts;
    }
}
