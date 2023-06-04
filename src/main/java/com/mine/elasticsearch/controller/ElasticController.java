package com.mine.elasticsearch.controller;

import com.mine.elasticsearch.entity.ESPost;
import com.mine.elasticsearch.repository.IESPostRepository;
import com.mine.elasticsearch.service.IESPost;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/search-post")
public class ElasticController {
    IESPostRepository postRepository;
    IESPost postService;
    public ElasticController(IESPost postService, IESPostRepository postRepository) {
        this.postService = postService;
        this.postRepository = postRepository;
    }
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/get")
    public ESPost getPostById(@RequestParam String id){
        return postService.getPostById(id);
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/get-all")
    public List<ESPost> getAllPost(@RequestBody String stringJson){
        return postService.searchESPost(stringJson);
    }

}
