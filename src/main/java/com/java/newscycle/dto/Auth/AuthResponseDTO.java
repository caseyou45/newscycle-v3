package com.java.newscycle.dto.Auth;

import lombok.Data;

@Data
public class AuthResponseDTO {
    private String accessToken;
    private String tokenType = "Bearer ";

    private Integer id;
    private String username;

    public AuthResponseDTO(String accessToken, String username, Integer id) {
        this.accessToken = accessToken;
        this.username = username;
        this.id = id;
    }
}