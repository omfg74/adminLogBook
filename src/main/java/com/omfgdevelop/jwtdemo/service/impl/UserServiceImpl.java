package com.omfgdevelop.jwtdemo.service.impl;

import com.omfgdevelop.jwtdemo.model.Role;
import com.omfgdevelop.jwtdemo.model.Status;
import com.omfgdevelop.jwtdemo.model.User;
import com.omfgdevelop.jwtdemo.repository.RoleRepository;
import com.omfgdevelop.jwtdemo.repository.UserRepository;
import com.omfgdevelop.jwtdemo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(User user) {
        Role role = roleRepository.findByName("ROLE_USER");
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(role);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(userRoles);
        user.setStatus(Status.ACTIVE);
        User registerUser = userRepository.save(user);
        System.out.println("Register " + registerUser);
        return registerUser;
    }

    @Override
    public List<User> getAll() {
        List<User> result = userRepository.findAll();
        System.out.println("Users count " + result.size());
        return result;
    }

    @Override
    public User findByUserName(String username) {
        User result = userRepository.findByUserName(username);
        System.out.println("User found " + result.getUserName());
        return result;
    }

    @Override
    public User findById(Long id) {
        User result = userRepository.findById(id).orElse(null);
        if (result == null) {
            System.out.println("Nothing found ");
            return null;
        }
        return result;
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
        System.out.println("User deleted " + id);
    }
}
