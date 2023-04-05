package com.mine.service;

import com.mine.entity.User;
import com.mine.client.user.dto.UserDtoReq;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface IUser {

    ResponseEntity<?> login(@RequestBody UserDtoReq request);
    ResponseEntity<?> register(@RequestBody UserDtoReq request);

    User findUserByEmail(String email);




}
