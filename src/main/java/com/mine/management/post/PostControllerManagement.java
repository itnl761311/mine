package com.mine.management.post;

import com.mine.client.dto.PostDtoReq;
import com.mine.service.IPost;
import com.mine.service.IUser;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/management/post")
public class PostControllerManagement {

    IPost iPost;

    IUser iUser;

    public PostControllerManagement(IPost iPost, IUser iUser){
        this.iPost = iPost;
        this.iUser = iUser;
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/get-all")
    public ResponseEntity<?> getAll(@RequestBody PostDtoReq postDtoReq){
        return iPost.findAll(postDtoReq);
    }
}
