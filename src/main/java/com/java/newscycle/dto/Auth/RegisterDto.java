package com.java.newscycle.dto.Auth;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterDTO {
    private String username;
    private String password;
}