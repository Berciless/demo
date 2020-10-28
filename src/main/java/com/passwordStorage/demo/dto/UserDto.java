package com.passwordStorage.demo.dto;

import com.passwordStorage.demo.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private String username;

    private String password;

    private String type;

    public UserDto(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.type = user.getType();
    }
}
