package com.omfgdevelop.adminLogBook.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.omfgdevelop.adminLogBook.model.Status;
import com.omfgdevelop.adminLogBook.model.User;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AdminUserDto {
    private Long id;
    private String username;
    private String firstName;
    private String lastname;
    private String email;
    private String status;

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
        user.setStatus(Status.valueOf(status));
        return user;
    }

    public static AdminUserDto fromUser(User user) {
        AdminUserDto userDto = new AdminUserDto();
        userDto.id = user.getId();
        userDto.firstName = user.getFirstname();
        userDto.lastname = user.getLastName();
        userDto.email = user.getEmail();
        userDto.status = user.getStatus().name();
        return userDto;
    }
}
