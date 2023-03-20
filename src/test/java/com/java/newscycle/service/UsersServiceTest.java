package com.java.newscycle.service;

import com.java.newscycle.entity.Users;
import com.java.newscycle.repository.CommentRepository;
import com.java.newscycle.repository.UsersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UsersServiceTest {

    @Mock
    private UsersRepository usersRepository;

    @Mock
    private CommentRepository commentRepository;

    private UsersService usersService;

    @BeforeEach
    void setUp() {
        this.usersService = new UsersService(this.usersRepository, this.commentRepository);
    }

    @Test
    void getUserByUsername() {
        Users user = new Users();
        user.setUsername("testuser");
        user.setPassword("testpassword");

        when(usersRepository.findUsersByUsername("testuser")).thenReturn(user);
        Users user1 = usersService.getUserByUsername("testuser");
        verify(usersRepository).findUsersByUsername(("testuser"));

        assertEquals(user1, user);


    }

    @Test
    void userSignUp() {

        Users user = new Users();
        user.setUsername("testuser");
        user.setPassword("testpassword");
        usersService.userSignUp(user);
        verify(usersRepository).save(user);


    }

    @Test
    void userSignIn() {
        Users user = new Users();
        user.setUsername("testuser");
        user.setPassword("testpassword");
        usersService.userSignIn(user);
        verify(usersRepository).findUsersByUsername(user.getUsername());
    }
}