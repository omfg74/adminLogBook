package com.omfgdevelop.adminLogBook.security;

import com.omfgdevelop.adminLogBook.model.User;
import com.omfgdevelop.adminLogBook.security.jwt.JwtUser;
import com.omfgdevelop.adminLogBook.security.jwt.JwtUserFactory;
import com.omfgdevelop.adminLogBook.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class JwtUserDetailsService implements UserDetailsService {

    private final UserService userService;

    public JwtUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUserName(username);

        if (user == null) {
            throw new UsernameNotFoundException("User with name: " + username + " not found");
        }
        JwtUser jwtUser = new JwtUserFactory().create(user);
        System.out.println("in loadUserByUserName user " + username + " loaded");
        return jwtUser;
    }
}
