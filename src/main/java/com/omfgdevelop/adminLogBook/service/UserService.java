package com.omfgdevelop.adminLogBook.service;

import com.omfgdevelop.adminLogBook.model.User;

import java.util.List;

public interface UserService {

    User register(User user);

    List<User> getAll();

    User findByUserName(String username);
    User findByEmail(String username);

    User findById(Long id);

    void delete(Long id);
}
