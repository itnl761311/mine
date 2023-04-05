package com.mine.client.post.controller;

import com.mine.client.post.dto.PostDtoReq;
import com.mine.entity.User;
import com.mine.repository.PostRepository;
import com.mine.service.IPost;
import com.mine.service.IUser;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post")

public class PostController {
    IPost iPost;
    IUser iUser;

    public PostController(IPost iPost, IUser iUser){
        this.iPost = iPost;
        this.iUser = iUser;
    }
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/get")
    public ResponseEntity<?> get(@RequestParam int id) {
        return iPost.get(id);
    }

    @PreAuthorize("hasAnyRole('USER')")
    @PostMapping("/get-all")
    public ResponseEntity<?> getAllByUserId(@RequestBody PostDtoReq postDtoReq){
        User user = iUser.findUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        postDtoReq.setUserId(user.getId());
        return iPost.getAllByUserId(postDtoReq);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PostMapping("/create")
    public ResponseEntity<?> createPost(@RequestBody PostDtoReq postDtoReq){
        return iPost.create(postDtoReq);
    }


}
