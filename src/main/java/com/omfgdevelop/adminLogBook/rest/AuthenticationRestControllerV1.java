package com.omfgdevelop.adminLogBook.rest;

import com.omfgdevelop.adminLogBook.dto.AuthenticationRequestDto;
import com.omfgdevelop.adminLogBook.dto.RegistrationDto;
import com.omfgdevelop.adminLogBook.exception.UserExistsException;
import com.omfgdevelop.adminLogBook.model.User;
import com.omfgdevelop.adminLogBook.security.jwt.JwtTokenProvider;
import com.omfgdevelop.adminLogBook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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

    private Map<Object, Object> createToken(User user) {
        String token = jwtTokenProvider.createToken(user.getUserName(), user.getRoles(), user.getId());

        Map<Object, Object> response = new HashMap<>();
        response.put("username", user.getUserName());
        response.put("token", token);
        return response;
    }

    @PostMapping("login")
    public ResponseEntity<Map<Object, Object>> login(@ModelAttribute AuthenticationRequestDto requestDto) {

        try {
            String userName = requestDto.getUserName();
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, requestDto.getPassword()));
            User user = userService.findByUserName(userName);
            if (user == null) {
                throw new UsernameNotFoundException("User with username " + userName + " not found");
            }
            Map<Object, Object> response = createToken(user);
//            response.put("user", user);

            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("INvalid Username or password");
        }
    }

    @PostMapping(path = "register")
    public ResponseEntity<Map<Object, Object>> register(@ModelAttribute RegistrationDto registrationDto) {
        try {
            User existUser = userService.findByEmail(registrationDto.getEmail());
            if (existUser != null) {
                throw new UserExistsException("User already exists");
            }
            User user = new User();
            user.setEmail(registrationDto.getEmail());
            user.setFirstname(registrationDto.getFirstName());
            user.setUserName(registrationDto.getUserName());
            user.setLastName(registrationDto.getLastName());
            user.setPassword(registrationDto.getPassword());
            User registeredUser = userService.register(user);
            Map<Object, Object> response = createToken(registeredUser);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BadCredentialsException("Registration Error");
        }
    }

}
