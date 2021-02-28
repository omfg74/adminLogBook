package com.omfgdevelop.adminLogBook.rest;

import com.omfgdevelop.adminLogBook.model.User;
import com.omfgdevelop.adminLogBook.security.jwt.JwtTokenProvider;
import com.omfgdevelop.adminLogBook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

abstract public class BaseController {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    protected User resolveUser(String authHeader) {
        String token = jwtTokenProvider.resolveTokenString(authHeader);
        Long id = jwtTokenProvider.getUserId(token);
        User user = userService.findById(id);
        return user;
    }
}
