package com.passwordStorage.demo.model;

import com.sun.istack.NotNull;
import lombok.*;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @NotNull
    private String username;
    @NotNull
    private String password;
}
