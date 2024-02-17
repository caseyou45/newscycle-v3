package com.java.newscycle.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import com.java.newscycle.dto.Auth.AuthResponseDTO;

public class AuthResponseDTOTest {

    @Test
    public void testAuthResponseDTOConstructor() {
        // Arrange
        String accessToken = "testAccessToken";
        String username = "testUsername";
        Long id = 123L;

        // Act
        AuthResponseDTO authResponseDTO = new AuthResponseDTO(accessToken, username, id);

        // Assert
        assertNotNull(authResponseDTO);
        assertEquals(accessToken, authResponseDTO.getAccessToken());
        assertEquals(username, authResponseDTO.getUsername());
        assertEquals(id, authResponseDTO.getId());
    }
}
