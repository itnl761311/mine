package com.mine.client.controller;

import com.mine.client.dto.UserDtoReq;
import com.mine.service.IUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

    //test
    @GetMapping("/login")
    public ResponseEntity<?> loginGet(@RequestParam String email, @RequestParam String password){
        UserDtoReq request = new UserDtoReq();
        request.setEmail(email);
        request.setPassword(password);
        return iUser.login(request);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDtoReq request){
        return iUser.register(request);
    }
}
