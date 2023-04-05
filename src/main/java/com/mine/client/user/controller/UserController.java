package com.mine.client.user.controller;

import com.mine.client.user.dto.UserDtoReq;
import com.mine.entity.User;
import com.mine.service.IUser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    IUser iUser;
    public UserController(IUser iUser) {
        this.iUser = iUser;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDtoReq request){
        return iUser.login(request);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDtoReq request){
        return iUser.register(request);
    }
}
