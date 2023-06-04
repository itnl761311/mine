package com.mine.elasticsearch.repository;

import com.mine.elasticsearch.entity.ESPost;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IESPostRepository extends ElasticsearchRepository<ESPost, String> {

}
