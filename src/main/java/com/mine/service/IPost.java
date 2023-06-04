package com.mine.service;

import com.mine.client.dto.PostDtoReq;
import org.springframework.http.ResponseEntity;

public interface IPost {

    ResponseEntity<?> get(int id);

    ResponseEntity<?> findAllByUserId(PostDtoReq postDtoReq);
    ResponseEntity<?> create(PostDtoReq postDtoReq);

    ResponseEntity<?> searchPostByCondition(String condition);


}
