package com.mine.elasticsearch.controller;

import com.mine.elasticsearch.entity.ESPost;
import com.mine.elasticsearch.service.IESPost;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/search-post")
public class ElasticController {
    IESPost postService;
    public ElasticController(IESPost postService) {
        this.postService = postService;
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/get")
    public ESPost getPostById(@RequestParam String id){
        return postService.getPostById(id);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/get-post-by-title-and-content")
    public List<ESPost> getPostByTitleAndContent(@RequestParam String title,@RequestParam String content){
        return postService.findESPostByTitleAndContent(title,content);
    }
//
//    @PreAuthorize("hasRole('USER')")
//    @GetMapping("/get/{email}")
//    public EPost getPostByEmail(@PathVariable String email){
//        EPost post = postRepository.findEPostByEmail(email);
//        return post;
//    }
}
