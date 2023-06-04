package com.mine.elasticsearch.service;

import com.mine.elasticsearch.entity.ESPost;
import com.mine.elasticsearch.repository.IESPostRepository;
import com.mine.elasticsearch.repository.defination.IESPostDefinitionRepository;
import com.mine.util.binarytree.ConditionTree;
import com.mine.util.elasticsearch.Convert;
import com.mine.util.elasticsearch.VisitNode;

import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ESPostImpl implements IESPost {
    ElasticsearchOperations operations;
    IESPostRepository postRepository;
    IESPostDefinitionRepository iesPostDefinitionRepository;
    public ESPostImpl(ElasticsearchOperations operations, IESPostRepository postRepository, IESPostDefinitionRepository iesPostDefinitionRepository){
        this.operations = operations;
        this.postRepository = postRepository;
        this.iesPostDefinitionRepository = iesPostDefinitionRepository;
    }
    @Override
    public ESPost getPostById(String id) {
        return postRepository.findById(id).orElse(null);
    }

    @Override
    public List<ESPost> searchESPost(String stringJson) {
        Query query = buildQuery(stringJson);
        return iesPostDefinitionRepository.searchAll(query,operations);
    }

    public Query buildQuery(String stringJson){
        Map map = Convert.firstConvertJsonToMap(stringJson);
        ConditionTree.AbsTree absTree = Convert.buildFilter(map);
        VisitNode node = new VisitNode();
        Criteria visit = node.visit(absTree);
        return new CriteriaQuery(visit);
    }
}
