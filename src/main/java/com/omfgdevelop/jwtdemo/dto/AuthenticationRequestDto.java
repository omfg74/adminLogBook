package com.omfgdevelop.jwtdemo.dto;

import lombok.Data;

@Data
public class AuthenticationRequestDto {
    private String userName;
    private String password;

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
