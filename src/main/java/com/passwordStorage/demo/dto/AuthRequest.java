package com.passwordStorage.demo.dto;


import com.passwordStorage.demo.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {
    private String username;
    private String password;

    public AuthRequest(User su){
        this.username = su.getUsername();
        this.password = su.getPassword();
    }
}
