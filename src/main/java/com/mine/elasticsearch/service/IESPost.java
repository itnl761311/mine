package com.mine.elasticsearch.service;
import com.mine.elasticsearch.entity.ESPost;
import java.util.List;

public interface IESPost {
    ESPost getPostById(String id);

    List<ESPost> findESPostByTitleAndContent(String title, String content);
}
