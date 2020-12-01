package com.omfgdevelop.jwtdemo.rest;

import com.omfgdevelop.jwtdemo.dto.AuthenticationRequestDto;
import com.omfgdevelop.jwtdemo.model.User;
import com.omfgdevelop.jwtdemo.security.jwt.JwtTokenProvider;
import com.omfgdevelop.jwtdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping(value = "/api/v1/auth/")
public class AuthenticationRestControllerV1 {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserService userService;

    public AuthenticationRestControllerV1(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @PostMapping("login")
    public ResponseEntity login(@RequestBody AuthenticationRequestDto requestDto) {

        try {
            String userName = requestDto.getUserName();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, requestDto.getPassword()));
            User user = userService.findByUserName(userName);
            if (user == null) {
                throw new UsernameNotFoundException("User with username " + userName + " not found");

            }
            String token = jwtTokenProvider.createToken(userName, user.getRoles());

            Map<Object, Object> response = new HashMap<>();
            response.put("username", userName);
            response.put("token", token);

            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("INvalid Username or password");
        }
    }
}
