package com.mine.controller;

import com.mine.entity.User;
import com.mine.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class TestController {
    @Autowired
    UserRepository userRepository;
    @GetMapping("/index")
    public String getTest(){
        User user = userRepository.findUserById(1);
        return user.toString();
    }
}
