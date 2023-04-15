package com.mine.client.user.controller;

import com.mine.client.user.dto.UserDtoReq;
import com.mine.entity.User;
import com.mine.repository.DemoFilter;
import com.mine.service.IUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    IUser iUser;

    DemoFilter demoFilter;
    public UserController(IUser iUser, DemoFilter demoFilter) {
        this.iUser = iUser;
        this.demoFilter = demoFilter;
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
