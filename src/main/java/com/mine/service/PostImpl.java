package com.mine.service;

import com.mine.client.dto.PostDtoReq;
import com.mine.client.dto.PostDtoRes;
import com.mine.entity.Post;
import com.mine.entity.User;
import com.mine.repository.PostRepository;
import com.mine.repository.UserRepository;
import com.mine.util.Convenience;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class PostImpl implements IPost{
    PostRepository postRepository;
    UserRepository userRepository;
    public PostImpl(PostRepository postRepository, UserRepository userRepository){
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }
    @Override
    public ResponseEntity<?> get(int id) {

        PostDtoRes postDtoRes = new PostDtoRes();
        Post post = postRepository.findPostById(id);
        if(post == null){
            postDtoRes.setMessage("Get Post fail");
            postDtoRes.setCode(HttpStatus.BAD_REQUEST.value());
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(postDtoRes);
        }
        postDtoRes.setMessage("Get Post successful");
        postDtoRes.setCode(HttpStatus.OK.value());
        postDtoRes.setPosts(List.of(post));

        return ResponseEntity.status(HttpStatus.OK).body(postDtoRes);
    }

    @Override
    public ResponseEntity<?> findAll(PostDtoReq postDtoReq) {
        PostDtoRes postDtoRes = new PostDtoRes();
        Page<Post> pages = postRepository.findAll(postDtoReq.getUserId(), PageRequest.of(postDtoReq.getPage(),postDtoReq.getSize(), Sort.by("id").ascending()));
        postDtoRes.setMessage("Get All Post successful");
        postDtoRes.setCode(HttpStatus.OK.value());
        postDtoRes.setPosts(pages.getContent());
        return ResponseEntity.status(HttpStatus.OK).body(postDtoRes);
    }

    @Transactional
    @Override
    public ResponseEntity<?> create(PostDtoReq postDtoReq){
        PostDtoRes postDtoRes = new PostDtoRes();
        User user = userRepository.findUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        Post post = new Post();
        post.setContent(postDtoReq.getContent());
        post.setTitle(postDtoReq.getTitle());
        //default post is publish
        post.setPublish(true);
        post.setUserId(user.getId());
        post.setCreateDate(Convenience.getCurrentDate());
        Post postResult = postRepository.save(post);

        postDtoRes.setMessage("Create Post successful");
        postDtoRes.setCode(HttpStatus.OK.value());
        postDtoRes.setPosts(List.of(postResult));

        return ResponseEntity.status(HttpStatus.OK).body(postDtoRes);
    }
}
