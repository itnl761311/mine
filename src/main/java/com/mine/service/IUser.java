package com.mine.service;

import com.mine.entity.User;

import java.util.List;

public interface IUser {
    User findUserById(int id);
    User findUserByEmail(String email);
    List<User> findAllUser();

}
