package com.omfgdevelop.jwtdemo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.omfgdevelop.jwtdemo.model.User;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {
    private Long id;
    private String username;
    private String firstName;
    private String lastname;
    private String email;

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User toUser() {
        User user = new User();
        user.setId(id);
        user.setUserName(username);
        user.setFirstname(firstName);
        user.setLastName(lastname);
        user.setEmail(email);
        return user;
    }

    public static UserDto fromUser(User user) {
        UserDto userDto = new UserDto();
        userDto.id = user.getId();
        userDto.firstName = user.getFirstname();
        userDto.lastname = user.getLastName();
        userDto.email = user.getEmail();
        return userDto;
    }
}
