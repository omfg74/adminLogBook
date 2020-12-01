package com.omfgdevelop.jwtdemo.repository;

import com.omfgdevelop.jwtdemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String name);

}
