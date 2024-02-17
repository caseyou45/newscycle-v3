package com.java.newscycle.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import com.java.newscycle.dto.Auth.RegisterDTO;

public class RegisterDTOTest {

    @Test
    public void testRegisterDTO() {
        // Arrange
        String username = "testUser";
        String password = "testPassword";

        // Act
        RegisterDTO registerDTO = new RegisterDTO(username, password);

        // Assert
        assertNotNull(registerDTO);
        assertEquals(username, registerDTO.getUsername());
        assertEquals(password, registerDTO.getPassword());
    }
}
