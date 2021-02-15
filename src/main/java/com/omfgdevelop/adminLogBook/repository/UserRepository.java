package com.omfgdevelop.adminLogBook.repository;

import com.omfgdevelop.adminLogBook.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String name);

}
