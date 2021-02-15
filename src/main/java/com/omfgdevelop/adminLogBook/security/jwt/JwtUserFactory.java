package com.omfgdevelop.adminLogBook.security.jwt;

import com.omfgdevelop.adminLogBook.model.Role;
import com.omfgdevelop.adminLogBook.model.Status;
import com.omfgdevelop.adminLogBook.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

public final class JwtUserFactory {

    public JwtUserFactory() {

    }

    public static JwtUser create(User user) {
        return new JwtUser(user.getId(),
                user.getUserName(),
                user.getFirstname(),
                user.getLastName(),
                user.getPassword(),
                user.getEmail(),
                user.getStatus().equals(Status.ACTIVE),
                user.getUpdated(),
                null);
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Role> userRoles) {
        return userRoles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
