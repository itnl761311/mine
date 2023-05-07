package com.mine.service;

import com.mine.entity.User;
import com.mine.client.dto.UserDtoReq;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface IUser {

    ResponseEntity<?> login(@RequestBody UserDtoReq request);
    ResponseEntity<?> register(@RequestBody UserDtoReq request);

    User findUserByEmail(String email);




}
