package com.mine.service;

import com.mine.entity.User;
import com.mine.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserImpl implements IUser{
    @Autowired
    UserRepository userRepository;

    @Override
    public User findUserById(int id) {
        return userRepository.findUserById(id);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public List<User> findAllUser() {
        return userRepository.findAll();
    }
}
