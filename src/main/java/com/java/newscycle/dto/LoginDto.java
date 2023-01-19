package com.java.newscycle.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data

public class LoginDto {
    private String username;
    private String password;
}