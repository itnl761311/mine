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
    public String getTest(@RequestParam(required = false) String email, @RequestParam(required = false) int id){
        User user = userRepository.findUserByEmail("hien@gmail.com");
        User userId = userRepository.findUserById(id);
        return "";
    }
}
