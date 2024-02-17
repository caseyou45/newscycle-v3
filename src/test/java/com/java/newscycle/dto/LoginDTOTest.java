package com.java.newscycle.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import com.java.newscycle.dto.Auth.LoginDTO;

public class LoginDTOTest {

    @Test
    public void testLoginDTO() {
        // Arrange
        String username = "testUser";
        String password = "testPassword";

        // Act
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setUsername(username);
        loginDTO.setPassword(password);

        // Assert
        assertNotNull(loginDTO);
        assertEquals(username, loginDTO.getUsername());
        assertEquals(password, loginDTO.getPassword());
    }
}
