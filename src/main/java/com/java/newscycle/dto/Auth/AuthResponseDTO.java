package com.java.newscycle.dto.Auth;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthResponseDTO {

    private String accessToken;
    private String username;
    private Long id;

}