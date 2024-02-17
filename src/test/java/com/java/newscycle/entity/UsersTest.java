package com.java.newscycle.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.java.newscycle.dto.Likes.LikeDTO;

public class UsersTest {

    @Test
    public void testUsersEntityConstructor() {
        // Arrange
        Long id = 1L;
        String username = "testUser";
        String password = "password123";
        Date creationDate = new Date();
        List<Like> likes = new ArrayList<>();
        List<Comment> comments = new ArrayList<>();
        List<Role> roles = new ArrayList<>();
        List<LikeDTO> likeDTOs = new ArrayList<>();

        // Act
        Users user = new Users(id, username, password, creationDate, likes, comments, roles, likeDTOs);

        // Assert
        assertNotNull(user);
        assertEquals(id, user.getId());
        assertEquals(username, user.getUsername());
        assertEquals(password, user.getPassword());
        assertEquals(creationDate, user.getCreationDate());
        assertEquals(likes, user.getLikes());
        assertEquals(comments, user.getComments());
        assertEquals(roles, user.getRoles());
        assertEquals(likeDTOs, user.getLikeDTOs());
    }
}
